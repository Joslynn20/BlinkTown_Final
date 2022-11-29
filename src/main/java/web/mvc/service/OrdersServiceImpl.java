package web.mvc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
	
	private final OrdersRepository ordersRep;
	private final ProductRepository productRep;
	private final OrderdetailsRepository orderdetailsRep;

	@Override
	public Page<Orders> selectAllOrders(int inCase, Users users, String startDate, String finalDate, Pageable pageable) {
		Page<Orders> ordersList=null;
		String usersId=null;
		
		if(inCase==1) { //관리자페이지-main 주문내역(페이징처리)
			
			ordersList=ordersRep.findAll(pageable);
			
		}else if(inCase==2) { //마이페이지-main 주문내역 10개 출력용 메소드		
			usersId=users.getUsersId();
			
			Pageable mainPage=PageRequest.of(0, 10, Direction.DESC, "usersId");
			ordersList=ordersRep.findByUsersId(usersId, mainPage);
			
		}else if(inCase==3) { //마이페이지-기간별 주문내역 조회	
			usersId=users.getUsersId();
			
			//String형식 날짜 Date로 변환
			SimpleDateFormat dateFormat= new SimpleDateFormat ("yyyyMMdd");
			Date dateStartDate=null;
			Date dateFinalDate=null;
			Date finalPlus=null;
			try {
				dateStartDate=dateFormat.parse(startDate);
				dateFinalDate=dateFormat.parse(finalDate);
				finalPlus=new Date(dateFinalDate.getTime() + (1000 * 60 * 60 * 24));
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("날짜 형식을 확인해주세요 ex)20221128");
			}
			ordersList=ordersRep
					.findByOrdersNoAndOrdersDateGreaterThanEqualAndOrdersDateLessThan
					(usersId, dateStartDate, finalPlus, pageable);
		}
		return ordersList;
	}

	@Override
	public Page<Orderdetails> selectAllOrdersdetails(Orders orders, Pageable pageable) {
		Long ordersNo=orders.getOrdersNo();
		Page<Orderdetails> orderdetailsList=orderdetailsRep.findByOrdersNo(ordersNo, pageable);
		return orderdetailsList;
	}

	@Override /*다시 손보기...다시봐야함 */
	public String selectCheckBeforeOrders(Users users, Orders ordersProduct) {
//		//반복문 사용해서 List 속의 상품들 꺼내서 비교하기
//				List<Orderdetails> cartList=ordersProduct.getOrderdetailsList();
//				for(Orderdetails orderdetails : cartList){
//					//1) 멤버쉽 주문이 맞는지 조회 (Product ordersProduct)
//					if(orderdetails.getProduct().getGoods().getGoodsMembershiponly()==1){
//						if(users.getUsersMembership()==0) throw new RuntimeException("멤버쉽 회원만 주문 가능한 상품입니다");
//					}
//					//2) 상품이 멤버쉽카드인지 조회
//					if(orderdetails.getProduct().getProductCode()=="멤버쉽카드의 상품코드"){
//						if(users.getUsersMembership()==1) throw new RuntimeException("이미 멤버쉽 회원입니다");
//					}
//
//					//3) 상품 재고량이 주문 가능한 숫자인지 조회
//					Optional<Product> product=productRep.findById(orderdetails.getProduct().getProductCode());
//					if(product.get().getProductStock()>=0){
//						if(orderdetails.getProduct().getProductStock()>product.get().getProductStock() || product.get().getProductStock()==0)
//								throw new RuntimeException("상품 재고량이 부족합니다. 개수를 확인해주세요.");
//					}
//					
//				}//장바구니 리스트 꺼내서 유효성 체크하는 for문끝
		return null;
	}

	@Override
	public void insertOrdersOrderdetails(Users users, Orders ordersProduct) {
		// TODO Auto-generated method stub

	}

}
