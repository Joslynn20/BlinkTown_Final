package web.mvc.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.engine.query.spi.ReturnMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;
import web.mvc.domain.Product;
import web.mvc.domain.Users;
import web.mvc.dto.Cart;
import web.mvc.service.OrdersService;
import web.mvc.session.Session;

//@RestController
@Controller
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersVerifyController ordersVerifyController;
	@Autowired
	private CartController cartController;
	
	/**상수관리*/
	private final static int PAGE_COUNT=10;//페이지당 출력 숫자
	private final static int BLOCK_COUNT=10;//
	private final static String STATUS_BEFORE="결제중";
	private final static String STATUS_AFTER="결제완료";
	
	//유저 정보 받아오기 : Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	///////////////////////////////////////////////////////
	
	/**
	 * 1-1. 유저 마이페이지 메인
	 * 
	 * 1-1) 넘어오기 전 : 아마도 유저/마이페이지
	 * 1-2) 넘어오는 인수 : (현재 페이지: int nowPage), (날짜조회 시작일: String startDate), 
	 *                   (날짜조회 마지막일: String finalDate)
	 *                  ->startDate,finalDate=="20221202" 형식으로 날짜 입력 필요!
	 * 
	 * 2-1) 보내는 곳 : 유저(일반,멤버쉽)/마이페이지/주문페이지
	 * 2-2) 보내는 인수 : Page<Orders> orderList(주문리스트),
	 *                 int blockCount(페이징처리 블럭 수), 
	 *                 int startPage(시작 페이지), int nowPage(현재 페이지)
	 * 
	 * 기능들
	 * 3-1) 유저-주문 전체 조회
	 * 3-2) 유저-주문 기간별 조회 -> 우선 안쓰는 방향 //새 메소드로 구분짓기(url매핑 구분하기)
	 */
	@RequestMapping("/유저/마이페이지/주문페이지") //기간별 조회도 뒷순위->차후 안쓰면 정리하기
	public void selectAllOrders(Model model, 
			@RequestParam(defaultValue="1") int nowPage, String startDate, String finalDate) {
		
		Pageable ordersPage=PageRequest.of(nowPage-1, PAGE_COUNT, Direction.DESC, "ordersDate");
		Page<Orders> ordersList=null;
		//유저정보 받아와서 users에 넣기
		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//유저 메인페이지 정보 호출 : (inCase==2)
		if(startDate==null||finalDate==null) {
			//10개만 출력시 주석 풀기
//			usersId=users.getUsersId();
//			Pageable mainPage=PageRequest.of(0, 10, Direction.DESC, "ordersDate");
//			ordersList=ordersService.selectAllOrders(2, users, null, null, mainPage);
			
			ordersList=ordersService.selectAllOrders(2, users, null, null, ordersPage);
		}else {
		//기간 조회용 페이지 정보 호출 : (inCase==3) -> 차후 삭제하거나 하기(현재로선 안쓰는 방향)
			ordersList=ordersService.selectAllOrders(3, users, startDate, finalDate, ordersPage);
		}
		model.addAttribute("ordersList", ordersList);
		///////////////////////////////////////////////////
		//페이징 처리 메소드
		int temp=0;
		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
			temp=(nowPage-1)%BLOCK_COUNT;
		}
		int startPage=nowPage-temp;
		//페이징 처리 위한 정보들 값으로 넣어 전달
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
	}//주문전체 끝(유저)

	/**
	 * 1-2. 유저 마이페이지 - 주문 상세내역 
	 * -> 주문 내역 클릭시 해당 주문 코드로 주문 내역 조회
	 * 
	 * 1-1) 넘어오기 전 : 유저/마이페이지/주문페이지
	 * 1-2) 넘어오는 인수 : URL(Long ordersNo(주문번호))
	 * 
	 * 2-1) 보내는 곳 : 유저(일반,멤버쉽)/마이페이지/주문상세페이지(혹은 주문페이지)
	 * 2-2) 보내는 인수 : List<Orderdetails> orderdetailsList(상세주문리스트), 
	 *                 -> 주문상세에 해당하는 상품정보 포함(join)
	 *                 ->Page에서 List로 변경
	 * 
	 * 기능들
	 * 3) 주문 번호별 주문상세 전체 조회
	 */
	@RequestMapping("/유저/마이페이지/주문상세페이지/{ordersNo}") //페이징처리 주석처리
	public void selectAllOrderdetails(Model model, @PathVariable Long ordersNo) {
		List<Orderdetails> orderdetailsList=ordersService.selectAllOrderdetails(ordersNo);
		model.addAttribute("orderdetailsList", orderdetailsList);
	}//유저 상세조회 끝
	
	////////////////////////////////////////////////////
	
	/**
	 * 2-1. 관리자 페이지 - 주문 조회
	 * 
	 * 1-1) 넘어오기 전 : 관리자페이지
	 * 1-2) 넘어오는 인수 : (현재 페이지: int nowPage)
	 * 
	 * 2-1) 보내는 곳 : 관리자/주문페이지
	 * 2-2) 보내는 인수 : Page<Orders> orderList(주문리스트),
	 *                 int blockCount(페이징처리 블럭 수), 
	 *                 int startPage(시작 페이지), int nowPage(현재 페이지)
	 * 
	 * 기능들
	 * 3) 관리자-주문 전체 출력 페이지
	 */
	@RequestMapping("/관리자/주문페이지")
	public void selectAllOrdersAdmin(Model model,
			@RequestParam(defaultValue="1") int nowPage) {
		Pageable ordersPage=PageRequest.of(nowPage-1, PAGE_COUNT, Direction.DESC, "ordersDate");
		Page<Orders> ordersList=ordersService.selectAllOrders(3, null, null, null, ordersPage);
		model.addAttribute("ordersList", ordersList);
		/////////////////////////////////////////////////
		//페이징 처리 메소드
		int temp=0;
		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
			temp=(nowPage-1)%BLOCK_COUNT;
		}
		int startPage=nowPage-temp;
		//페이징 처리 위한 정보들 값으로 넣어 전달
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
	}//관리자 주문 리스트 끝
	
	/**
	 * 2-2. 관리자 마이페이지 - 주문 상세내역 
	 * -> 주문 내역 클릭시 해당 주문 코드로 주문 내역 조회
	 * 
	 * 1-1) 넘어오기 전 : 유저/마이페이지/주문페이지
	 * 1-2) 넘어오는 인수 : URL(Long ordersNo(주문번호))
	 * 
	 * 2-1) 보내는 곳 : 관리자/주문상세페이지(혹은 주문페이지)
	 * 2-2) 보내는 인수 : List<Orderdetails> orderdetailsList(상세주문리스트), 
	 *                 -> 주문상세에 해당하는 상품정보 포함(join)
	 *                 ->Page에서 List로 변경
	 * 
	 * 기능들
	 * 3) 주문 번호별 주문상세 전체 조회
	 */
	@RequestMapping("/관리자/주문상세페이지/{ordersNo}")
	public void selectAllOrderdetailsAdmin(Model model, @PathVariable Long ordersNo) {
		List<Orderdetails> orderdetailsList=ordersService.selectAllOrderdetails(ordersNo);
		model.addAttribute("orderdetailsList", orderdetailsList);
	}//관리자 주문 상세 끝
	
	//////////////////////////////////////////////////
	/**테스트용 진입점*/
//	@RequestMapping("/ordersTestAfter/ordersFinalTest")
//	public void formtest(
//			String productCode, Integer cartQty, Integer cartPrice //테스트용
////			)
////			,
//			, Model model)
////			@RequestParam List<Cart> cartList)
//			{
//		//테스트용
//		Product product=Product.builder().productCode(productCode)
//				.productMembershipOnly(0)
//				.build();
//		Cart cart=new Cart(product, cartQty, cartPrice);
//		List<Cart> cartList=new ArrayList<Cart>();
//		cartList.add(cart);
//		model.addAllAttributes(cartList);
//		Users users=Users.builder().usersId("user").build(); 
//		//////////테스트용 생성///////////////////////////////////////////
//		//실 사용 security
////		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		//재고량 체크
//		ordersService.selectCheckBeforeOrders(users, cartList);
//	}
	///////////////////////////////////////////////////
	/**테스트용*/
	@RequestMapping("/ordersTestAfter/beforeOrdersTest")
	public void testBefore() {}
	/**테스트용 간편*/
	@RequestMapping("/ordersTest")
	public String testBe() {
		return "ordersTestAfter/beforeOrdersTest";
	}
///////////////////////////////////////////////////////////////////////////////
	/*바로주문*/
	@RequestMapping("/ordersTestAfter/ordersFinalTest")
	public void formtest(
			String productCode, Integer cartQty, Integer cartPrice , Model model){
//		Product product=Product.builder().productCode(productCode)
//				.productMembershipOnly(0)
//				.build();
//		Orderdetails orderdetails=Orderdetails.builder().product(product)
//		.orderdetailsPrice(cartPrice).orderdetailsQty(cartQty).build();
		List<Orderdetails> orderdetailsList=new ArrayList<Orderdetails>();
//		orderdetailsList.add(orderdetails);
		model.addAllAttributes(orderdetailsList);
		Users users=Users.builder().usersId("user").build(); 
		//////////테스트용 생성///////////////////////////////////////////
		//실 사용 security
//		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//재고량 체크
//		ordersService.selectCheckBeforeOrders(users, cartList);
		Orders orders=Orders.builder().orderdetailsList(orderdetailsList).build();
		ordersService.selectCheckBeforeOrders(users, orders);
	}
	/**
	 * 주문폼으로 넘겨주는 진입점(장바구니 혹은 바로구매에서 진입)
	 * 상품 구매 버튼에 입력할 경로
	 * 
	 * 3-1. 일반회원 바로 주문하기/카트 주문하기
	 * 
	 * 1-1) 넘어오기 전 : {일반회원 혹은 멤버쉽회원}/상세페이지(바로구매 버튼)
	 * 1-2) 넘어오는 인수 : 상품번호-통일위해 List로(List<Product> productList),
	 *                   주문수량,가격(List<Orderdetails> orderdetailsList)
	 * 
	 * 2-1) 보내는 곳 : 주문폼
	 * 2-2) 보내는 인수 :  인수들 Hidden으로 넣어놓기
	 *                  상품번호-통일위해 List로(List<Product> productList),
	 *                  주문수량,가격(List<Orderdetails> orderdetailsList)
	 * 
	 * 기능들
	 * 3-1) MappingUrl통해서 접근권한 구분:멤버쉽상품
	 *   ->전달 받은 값 그대로 주문폼으로 전달
	 * 3-2) 주문전 체크 : 이상 발생시 RunTimeException 발생
	 *   -> 주문상품-카테고리 : 주문상품의 카테고리 확인하여 멤버쉽과 유료상품이 매치되는지 체크 / 멤버쉽카드 인지도 체크 (주문전 불가체크:1.유무료/2.카드재구매)
	 * 3-3) 주문전 체크 : 이상 발생시 RunTimeException 발생
	 *   -> 주문수량-상품재고량 : if 재고량이 1이상일때 / 재고량==0이거나, 재고량-구매수량<0 이면 실패
	 */
//	@RequestMapping("/user/beforeOrdersForm")
//	public String checkBeforeDirectOrders(
//			String productCode, Integer cartQty, Integer cartPrice
//			,
////			)
//			@RequestParam List<Cart> cartList) 
//			{
//		//테스트용
//		Product product=Product.builder().productCode(productCode)
//				.productMembershipOnly(0)
//				.build();
//		Cart cart2=new Cart(product, cartQty, cartPrice);
////		List<Cart> cartList=new ArrayList<Cart>();
////		model.addAllAttributes(cartList);
//		cartList.add(cart2);
//		Users users=Users.builder().usersId("user").build(); 
//		///테스트용 생성//
//		
////		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		//권한과 맞는지 설정
//		for(Cart cart:cartList) {
//			if (cart.getProduct().getProductMembershipOnly()==1) 
//				throw new RuntimeException("멤버쉽 가입 후 이용해 주세요");
//		}
//		//재고량 체크
//		ordersService.selectCheckBeforeOrders(users, cartList);
////		return "/orders/ordersForm";
//		return "/ordersTest/ordersFinalTest";//테스트용 뷰 연결
//	}

	/**
	 * 3-2. 멤버쉽회원 바로 주문하기/카트 주문하기
	 * @param cartList
	 * @return
	 */
//	@RequestMapping("/member/beforeOrdersForm")
//	public String memberCheckBeforeDirectOrders(
//			String productCode, Integer cartQty, Integer cartPrice //테스트용
////			)
//			,
//			@RequestParam List<Cart> cartList) 
//			{
//		//테스트용
//		Product product=Product.builder().productCode(productCode)
//				.productMembershipOnly(0)
//				.build();
//		Cart cart=new Cart(product, cartQty, cartPrice);
////		List<Cart> cartList=new ArrayList<Cart>();
////		model.addAllAttributes(cartList);
//		cartList.add(cart);
//		Users users=Users.builder().usersId("user").build(); 
//		///테스트용 생성//
//		//실 사용 security
////		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		//재고량 체크
//		ordersService.selectCheckBeforeOrders(users, cartList);
////		return "/orders/ordersForm";
//		return "/ordersTest/ordersFinalTest";//테스트용 뷰 연결
//	}
///////////////////////////////////////////////////////////////	
	/**
	 * 4. 주문
	 * 
	 * 1-1) 넘어오기 전 : 주문폼
	 * 1-2) 넘어오는 인수 : 상품번호 List로(List<Product> productList),
	 *                   (Hidden속 정보-Orders에 합쳐질 것으로 예상
	 *                     : 카트정보-수량,금액등등 (List<Orderdetails> orderdetailsList))
	 *                   주문폼 내용-주문정보(Orders orders)
	 *                   
	 * 2-1) 보내는 곳 : 주문폼 (에서 결제창 호출에 사용할 예정)
	 * 2-2) 보내는 인수 : Long ordersNo (주문번호),
	 *                 int amount (총계)
	 * 
	 * 기능들 - ajax처리예정 return값 Model에 넣기
	 * 3-1) 주문테이블+주문상세테이블에 주문정보 저장
	 * 3-2) 주문폼으로 ajax로 보내어 결제창 호출에 사용
	 *     ->결제완료후 검증까지 필요 ->이후 세션 삭제
	 * 
	 */
	@RequestMapping("/orders/checkout")
	@ResponseBody
	public Map<String, Object>  insertOrdersOrderdetails(
			Orders orders
//			, 
//			@RequestBody(value = "cartList") 
//			@RequestParam List<Cart> cartList //List엔 RequestParam사용
//			, HttpSession session)
			){
		//orderdetails값 넣는수단 강구 필요->DTO사용할 것
		
		//받은 카트 리스트를 주문에 담기
//		List<Orderdetails> orderdetailsList=new ArrayList<Orderdetails>();
//		Orderdetails beforedetails=new Orderdetails();
//		for(Cart cart:cartList) {
//			beforedetails.setProduct(cart.getProduct());
//			beforedetails.setOrderdetailsPrice(cart.getCartPrice());
//			beforedetails.setOrderdetailsQty(cart.getCartQty());
//			orderdetailsList.add(beforedetails);
//		}
//		orders.setOrderdetailsList(orderdetailsList);	
		////////////////////////////////////////////////////
		System.out.println("checkout출력 테스트 = " + orders.getOrdersAddr());

		List<Orderdetails> orderdetailsList=orders.getOrderdetailsList();
		int amount=0;
		for(Orderdetails orderdetails : orderdetailsList){
			amount+=orderdetails.getOrderdetailsPrice();
		}
		
		System.out.println("ordersdetailsList"+orderdetailsList); //값 안들어오니까 dto사용할 것
		System.out.println("amount="+amount);
		amount=1000;//테스트용
		
		//주문상태 결제중으로 입력
		orders.setOrdersStatus(STATUS_BEFORE);
//		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users users=Users.builder().usersId("user").build(); //////////테스트용 생성
		
		//결제 창 호출위해 결과값 담기
//		Orders finishOrders=ordersService.insertOrdersOrderdetails(users, orders, cartList);
		Orders finishOrders=ordersService.insertOrdersOrderdetails(users, orders);
		
		//ajax로 리턴값 Map으로 보냄->jason 사용, mappedby ignore설정완료
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orders", finishOrders);
		map.put("amount", amount);
		System.out.println("checkout끝");//확인용 출력
		
		return map;
	}
	
	////////////////////////////////////////////////////////////////////
	/**
	 * 5. 결제 검증 메소드 (API사용 특성상 필요한 메소드)
	 * 
	 * 1) 검증하기 : 실패하면 RuntimeException 일으킴. 성공시엔 아무 변화 없음
	 * 2-1) 검증 성공시 : 주문상태 변경+장바구니 세션 삭제(서비스에 주문상태 변경 추가,테스트할것)
	 * ->뷰에서 마이페이지-주문페이지 혹은 주문완료 페이지로 연결
	 * 2-2) 검증 실패시 : 주문 삭제(서비스에 삭제 추가, 테스트할 것)
	 */
	@ResponseBody
	@RequestMapping("/verifyIamport")
	public void ordersVerifyPayment(String imp_uid, HttpSession session) {
		try {
		//검증메소드 호출 및 검증위한 변수 저장
		IamportResponse<Payment> resultData=ordersVerifyController.paymentByImpUid(imp_uid);
		Long ordersNo=Long.parseLong(resultData.getResponse().getMerchantUid());
		int verifyAmount=resultData.getResponse().getAmount().intValueExact();
		
		//금액을 비교하여 검증(금액 다를시 주문내역 삭제 및 재고량 원복후 runtimeException발생시킴)
		ordersService.verifyOrders(ordersNo, verifyAmount, STATUS_AFTER);
		
//		session.removeAttribute("cartList"); //세션 사용 안함, dto 삭제
		//이상 없을시 장바구니 세션 삭제->dto삭제 메소드 호출
		cartController.deleteAllCart(session);
		//장바구니DTO 삭제
		}catch (Exception e) {
			//결제취소 메소드 넣기
			//일단 필요없으니 보류
			System.out.println("오류 발생 : 결제금액 위조 (검증 실패)");
//			new RuntimeException("결제중 오류가 발생하였습니다");
		}
	}
	
	/**삭제 메소드*/
	@RequestMapping("/orders/delete")
	public void ordersDelete(Orders orders/* Long ordersNo */) {
		System.out.println("삭제 컨트롤러");
		Long ordersNo=orders.getOrdersNo();
		ordersService.deleteOrders(ordersNo);
		System.out.println("삭제 컨트롤러 끝");
	}
	
	/**성공시 성공페이지 혹은 마이페이지-상품리스트로 가기*/
	////////////////////////////////////////////////////////////////////
	//테스트용 메소드들
	/**성공 출력 페이지-테스트용도*/
	@RequestMapping("/ordersTest/ordersResult")
	public void resultUrl() {}
	
//	@ResponseBody
	@RequestMapping("/ordersTest/ordersTotalTest")
	public void insertTesturl(@RequestParam List<Cart> cartList) {
		//주문폼 테스트용 url
		
//		Cart cart=new Cart(Product.builder().productCode("A01").build(), 2000, 2);
//		Cart cart2=new Cart(Product.builder().productCode("A03").build(), 2000, 1);
//		cartList=new ArrayList<Cart>();
//		cartList.add(cart);
//		cartList.add(cart2);
//		model.addAttribute("cartList", cartList);
//		model.addAllAttributes(cartList);
		
//		List<Orderdetails> orderdetailsList=new ArrayList<Orderdetails>();
//			orderdetailsList.add(Orderdetails.
//					builder()
//					.orderdetailsPrice(1000)
//					.orderdetailsQty(1)
//					.product
//					(Product
//							.builder()
//							.productCode("A01")
//							.build())
//					.build());
//		model.addAllAttributes(orderdetailsList);
			
//		Orderdetails orderdetails=
//		Orderdetails.
//		builder()
//		.orderdetailsPrice(1000)
//		.orderdetailsQty(1)
//		.product
//		(Product
//				.builder()
//				.productCode("A01")
//				.build())
//		.build();
//		
//		model.addAttribute("orderdetails", orderdetails);
	}
	
	
	/////////////////////////////////////////
	//확장
	/**
	 * 결제 취소->주문시 결제 테이블 추가후 결제코드(tid)받아서 저장해야 함
	 */

	/**
	 * 주문 상태 변경(관리자 접근권한)
	 * 1) 주문 상태별 조회
	 * 2) 결제 완료 상태에서 배송중 으로 상태 변경
	 */
	
	////////////////////////////////////////////
	//확장2
	/**
	 * 배송 조회 : 운송장 정보 DB테이블 필요 (택배사 API이용)
	 */
	/**
	 * 결제 수단 추가 : paypal, 부트페이, 네이버페이 등등
	 */
}
