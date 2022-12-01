package web.mvc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;
import web.mvc.domain.Product;
import web.mvc.domain.Users;
import web.mvc.repository.OrderdetailsRepository;
import web.mvc.repository.OrdersRepository;
import web.mvc.repository.ProductRepository;
import web.mvc.repository.UsersRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersServiceImpl implements OrdersService {
	
	private final OrdersRepository ordersRep;
	private final ProductRepository productRep;
	private final OrderdetailsRepository orderdetailsRep;
	private final UsersRepository userRep;
	
	@Override
	public Page<Orders> selectAllOrders(int inCase, Users users, String startDate, String finalDate, Pageable pageable) {
		Page<Orders> ordersList=null;
//		String usersId=null;
		
		if(inCase==1) { //관리자페이지-main 주문내역(페이징처리)
			
			ordersList=ordersRep.findAll(pageable);
			
		}else if(inCase==2) { //마이페이지-main 주문내역 10개 출력용 메소드		
//			usersId=users.getUsersId();
			
			Pageable mainPage=PageRequest.of(0, 10, Direction.DESC, "usersId");
//			ordersList=ordersRep.findByUsersId(users, mainPage);
			ordersList=ordersRep.findByUsers(users, mainPage);
			
		}else if(inCase==3) { //마이페이지-기간별 주문내역 조회	
//			usersId=users.getUsersId();
			
			//String형식 날짜 Date로 변환
//			SimpleDateFormat dateFormat= new SimpleDateFormat ("yyyyMMdd");

			//String형식을 LocalDateTime으로 변환
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			
			LocalDateTime dateStartDate=null;
			LocalDateTime dateFinalDate=null;
			LocalDateTime finalPlus=null;
			
			try {
				dateStartDate=LocalDateTime.parse(startDate, formatter);
				dateFinalDate=LocalDateTime.parse(finalDate, formatter);
				finalPlus=dateFinalDate.plusDays(1);
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("날짜 형식을 확인해주세요 ex)20221128");
			}
			ordersList=ordersRep
					.findByUsersAndOrdersDateGreaterThanEqualAndOrdersDateLessThan
					(users, dateStartDate, finalPlus, pageable);
		}
		return ordersList;
	} //selectAllOrders end

	@Override
	public Page<Orderdetails> selectAllOrdersdetails(Orders orders, Pageable pageable) {
		
		Page<Orderdetails> orderdetailsList=orderdetailsRep.findByOrders(orders, pageable);
		
		return orderdetailsList;
		
	} //selectAllOrdersdetails end

	/* 체크만 한 후 RuntimeException 처리
	 * - 1) 멤버쉽 체크는 시큐리티 사용해야돼서 뷰->컨트롤러로 카트 담을 때 분류하는게 좋을듯 (1번 삭제)
	 * */
	/* 예외처리 안할거면 selectCheckBeforeOrders에서 String으로 "forward:" 리턴하여, 
	 * 아래 insertOrdersOrderdetails에서 Runtime대신 기본 리턴값 "redirect:" 를 리턴하여 받아서
	 * 장바구니에 담는 곳으로 되돌리는 경로 설정하면 어떨까 하지만,
	 * 우선 RunTimeException으로 설정*/
	@Override 
	public void selectCheckBeforeOrders(Users users, Orders ordersProduct) {
		//반복문 사용해서 List 속의 상품들 꺼내서 비교하기
				List<Orderdetails> cartList=ordersProduct.getOrderdetailsList();
				for(Orderdetails orderdetails : cartList){
//					//1) 멤버쉽 주문이 맞는지 조회 (Product ordersProduct)
//					if(orderdetails.getProduct().getGoods().getGoodsMembershiponly()==1){
//						if(users.getUsersMemberShip()==0) throw new RuntimeException("멤버쉽 회원만 주문 가능한 상품입니다");
//					}
					
					//2) 상품이 멤버쉽카드인지 조회
					if(orderdetails.getProduct().getProductCode()=="멤버쉽카드의 상품코드"){
						if(users.getUsersMemberShip()==1) throw new RuntimeException("이미 멤버쉽 회원입니다");
					}

					//3) 상품 재고량이 주문 가능한 숫자인지 조회
					Product product=productRep.findById(orderdetails.getProduct().getProductCode()).orElse(null);
					if(product.getProductStock()>=0){
						if(orderdetails.getProduct().getProductStock()>product.getProductStock() || product.getProductStock()==0)
								throw new RuntimeException("상품 재고량이 부족합니다. 개수를 확인해주세요.");
					}
					
				}//장바구니 리스트 꺼내서 유효성 체크하는 for문끝
	} //selectCheckBeforeOrders end

	//인수로 Orders에 한번에 담을 수 있는지 확인 후 안담아지면
	//List로 주문상세정보 받아서 추가로 담기
	@Override
	public void insertOrdersOrderdetails(Users users, Orders ordersProduct/*, List<Orderdetails> cartList*/) {
		//주문 체크 메소드 호출하여 주문전 체크
		this.selectCheckBeforeOrders(users, ordersProduct);
		//이상없다면 Exception없이 빠져나옴
		
		String usersId=users.getUsersId();
		
//		//상세정보 따로 담아야 하면 쓰기
//		List<Orderdetails> finishOrderdetailsList=orderdetailsRep.saveAll(cartList);
//		ordersProduct.setOrderdetailsList(finishOrderdetailsList);
		
		//insert
		Orders finishOrders=ordersRep.save(ordersProduct); //한 번에 insert 되면 이걸로 끝내기
		List<Orderdetails> finishOrderdetailsList=finishOrders.getOrderdetailsList();
		
		//저장값 꺼내서 상품에 해당하는 상품 재고량 감소 및 멤버쉽 수정 구현
		//if 재고량이 1이상일때 : 재고량 감소 / 재고량-상품수량<0 이면 실패
		//멤버쉽카드일시 :  위 재고량 로직+ 권한생성 + 회원 멤버쉽 1로 update
		for(Orderdetails orderdetails : finishOrderdetailsList){
			
			//상품 수정 기능구현을 위한 상품 조회
			String getProdCode=orderdetails.getProduct().getProductCode();
			Product product=productRep.findById(getProdCode).orElse(null);

			//1-1) 상품이 멤버쉽카드인지 조회
			//1-2) 멤버쉽 수정 기능구현을 위한 유저 조회 (혹은 인수 사용->인수에 모든 정보 없을듯하니 조회하기)
			//1-3) 유저 정보의 멤버쉽 업데이트
			if(getProdCode=="멤버쉽카드의 상품코드"){
				Users dbUsers=userRep.findById(usersId).orElse(null);
				dbUsers.setUsersMemberShip(1);
			}
			
			//2-1) 상품 재고량이 주문 가능한 숫자인지 조회 (0개 초과부터)
			//2-2) 상품 재고량 감소
			if(product.getProductStock()>0){
				product.setProductStock(product.getProductStock()-orderdetails.getOrderdetailsQty());
			}
			
		}//장바구니 리스트 꺼내서 유효성 체크하는 for문끝
	} //insertOrdersOrderdetails end

}
