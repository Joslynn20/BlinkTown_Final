package web.mvc.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
import web.mvc.service.OrdersService;

//@RestController
@Controller
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersVerifyController ordersVerifyController;
	
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
			ordersList=ordersService.selectAllOrders(2, users, null, null, ordersPage);
		}else {
		//기간 조회용 페이지 정보 호출 : (inCase==3) -> 차후 삭제하거나 하기(현재로선 안쓰는 방향)
			ordersList=ordersService.selectAllOrders(3, users, startDate, finalDate, ordersPage);
		}
		
		//페이징 처리 메소드
		int temp=0;
		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
			temp=(nowPage-1)%BLOCK_COUNT;
		}
		int startPage=nowPage-temp;
		
		model.addAttribute("ordersList", ordersList);
		
		//페이징 처리 위한 정보들 값으로 넣어 전달
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
	}

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
	public void selectAllOrderdetails(Model model, @PathVariable Long ordersNo
			/*, @RequestParam(defaultValue="1") int nowPage*/) {
		
//		Pageable ordersPage=PageRequest.of(nowPage-1, PAGE_COUNT, Direction.DESC, "orderdetailsNo");
		
//		Page<Orderdetails> orderdetailsList=ordersService.selectAllOrderdetails(ordersNo, ordersPage);
		List<Orderdetails> orderdetailsList=ordersService.selectAllOrderdetails(ordersNo);

		model.addAttribute("orderdetailsList", orderdetailsList);
		
		//페이징 처리 메소드
//		int temp=0;
//		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
//			temp=(nowPage-1)%BLOCK_COUNT;
//		}
//		int startPage=nowPage-temp;
//		
//		
//		//페이징 처리 위한 정보들 값으로 넣어 전달
//		model.addAttribute("blockCount", BLOCK_COUNT);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("nowPage", nowPage);
	}
	
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
		
		model.addAttribute(ordersList);
		
		//페이징 처리 메소드
		int temp=0;
		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
			temp=(nowPage-1)%BLOCK_COUNT;
		}
		int startPage=nowPage-temp;
		
		model.addAttribute("ordersList", ordersList);
		
		//페이징 처리 위한 정보들 값으로 넣어 전달
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
	}
	
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
	@RequestMapping("/관리자/주문상세페이지/{ordersNo}") //페이징처리 안하는 방향으로 우선 주석처리
	public void selectAllOrderdetailsAdmin(Model model, @PathVariable Long ordersNo
	/* ,@RequestParam(defaultValue="1") int nowPage */) {
		
//		Pageable ordersPage=PageRequest.of(nowPage-1, PAGE_COUNT, Direction.DESC, "ordersDate");
		
		List<Orderdetails> orderdetailsList=ordersService.selectAllOrderdetails(ordersNo);

		model.addAttribute("orderdetailsList", orderdetailsList);
		
		//페이징 처리 메소드
//		int temp=0;
//		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
//			temp=(nowPage-1)%BLOCK_COUNT;
//		}
//		int startPage=nowPage-temp;
//		
//		
//		//페이징 처리 위한 정보들 값으로 넣어 전달
//		model.addAttribute("blockCount", BLOCK_COUNT);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("nowPage", nowPage);
	}
	
	
	//////////////////////////////////////////////////
	/*
	 * 3) 주문 폼 이동
	 * -> 주문폼에서 다음카카오 주소지API사용, 스크립트만 하면 될지 확인해보고 완성할 것
	 * (메소드는 폼으로 연결)
	 * 
	 * 배송지 주소 API사용 -> 뷰에서 폼으로 작성 (자바스크립트)
	 */
	
	/**
	 * 
	 * 3-1. 일반회원 바로 주문하기-> 밑에서 통합
	 * 
	 * 1-1) 넘어오기 전 : 일반상품/상세페이지(바로구매 버튼)
	 * 1-2) 넘어오는 인수 : 상품번호-통일위해 List로(List<Product> productList),
	 *                   주문수량,가격(List<Orderdetails> orderdetailsList)
	 * 
	 * 2-1) 보내는 곳 : 주문폼
	 * 2-2) 보내는 인수 :  인수들 Hidden으로 넣어놓기
	 *                  상품번호-통일위해 List로(List<Product> productList),
	 *                  주문수량,가격(List<Orderdetails> orderdetailsList)
	 * 
	 * 기능들
	 * 3-1) MappingUrl통해서 접근권한 구분:일반회원
	 *   ->전달 받은 값 그대로 주문폼으로 전달
	 * 3-2) 주문전 체크 : 이상 발생시 RunTimeException 발생
	 *   -> 주문상품-카테고리 : 주문상품의 카테고리 확인하여 멤버쉽과 유료상품이 매치되는지 체크 / 멤버쉽카드 인지도 체크 (주문전 불가체크:1.유무료/2.카드재구매)
	 * 3-3) 주문전 체크 : 이상 발생시 RunTimeException 발생
	 *   -> 주문수량-상품재고량 : if 재고량이 1이상일때 / 재고량==0이거나, 재고량-구매수량<0 이면 실패
	 */
//	@RequestMapping("/일반회원/구매/주문폼") /**인수 List들 Hidden으로 넣기(?)*/
//	public String usersOrdersForm(List<Product> productList, List<Orderdetails> orderdetailsList /*, Model model*/) {
////		model.addAttribute(productList);
////		model.addAttribute(orderdetailsList);
//		
//		return "주문폼";
//	}
	
	/**
	 * 3-1. 일반회원 바로 주문하기
	 * 3-2. 멤버쉽회원 바로 주문하기
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
	@RequestMapping("/{author}/주문/주문체크")
	public String checkOrdersForm(List<Product> productList, List<Orderdetails> orderdetailsList/*, Model model*/) {
//		model.addAttribute(productList);
//		model.addAttribute(orderdetailsList);
		
		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		//insert에서도 사용
		//상품 번호로 상품 객체에 들어가는지 확인할 것->안될것같음
		//->현재 orderdetailsList에는 상품 객체로 들어가 있음
		//->Product의 List로 받아서 꺼내서 대입하여 상세주문에 넣기(index가 매치되니까 괜찮을듯)
		for(int i=0; i<productList.size(); i++) {
			orderdetailsList.get(i).setProduct(productList.get(i));
		}
		
		//받은 카트 리스트를 주문에 담기
		Orders orders=new Orders();
		orders.setOrderdetailsList(orderdetailsList);	
		
		//체크 메소드 호출
		ordersService.selectCheckBeforeOrders(users, orders);
		//이상 없을시 리턴
		
		return "/주문/주문폼";
	}
	
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
	@ResponseBody // 주문하기는 AJAX로 할 예정, (REST API사용) 위에서 RestController선언하면 주석처리
	@RequestMapping("/주문/주문하기")
	public Model insertOrdersOrderdetails(Model model, HttpSession session, Principal principal, Orders orders, List<Product> productList/*, List<Orderdetails> orderdetailsList*/) {
		
		//Orders에 한 번에 들어간다면 인수로 하나만 받기
		List<Orderdetails> orderdetailsList=orders.getOrderdetailsList();//안쓰면 주석처리하긴
		
		//상품 번호로 상품 객체에 들어가는지 확인할 것->안될것같음
		//->현재 orderdetailsList에는 상품 객체로 들어가 있음
		//->Product의 List로 받아서 꺼내서 대입하여 상세주문에 넣기(index가 매치되니까 괜찮을듯)
		//+추가로 총계 계산해서 결제하기 위해(카트담기 뿐만 아니라 바로주문에도 동일효과위해) 합계값 구하기  
		int amount=0;
		for(int i=0; i<productList.size(); i++) {
			orderdetailsList.get(i).setProduct(productList.get(i));
			amount+=orderdetailsList.get(i).getOrderdetailsPrice();
		}
		
		//받은 카트 리스트를 주문에 담기
		orders.setOrderdetailsList(orderdetailsList);
		
		//주문상태 결제중으로 입력
		orders.setOrdersStatus(STATUS_BEFORE);
		
		Users users=(Users)principal;
//		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//결제 창 호출위해 결과값 담기
		Orders finishOrders=ordersService.insertOrdersOrderdetails(users, orders);
		model.addAttribute("orders", finishOrders);
		
		//총결제금액 담기
		model.addAttribute("amount", amount);
		
		return model;
		
//		//장바구니 세션 사용하여 insert 완료 후 세션 삭제 
//		//-> 검증 메소드에서 완료후 삭제(아니면 전부 리셋해야돼서 세션 유지하고자 함)
//		session.removeAttribute("세션속 장바구니 상품 목록");
//		return "/주문/주문완료 페이지";
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
	@RequestMapping("/verifyIamport")//("/{imp_uid}")
	public void ordersVerifyPayment(String imp_uid, HttpSession session) {
		try {
		//검증메소드 호출 및 검증위한 변수 저장
		IamportResponse<Payment> resultData=ordersVerifyController.paymentByImpUid(imp_uid);
		Long ordersNo=Long.parseLong(resultData.getResponse().getMerchantUid());
		int verifyAmount=resultData.getResponse().getAmount().intValueExact();
		
		//금액을 비교하여 검증(금액 다를시 주문내역 삭제 및 재고량 원복후 runtimeException발생시킴)
		ordersService.verifyOrders(ordersNo, verifyAmount, STATUS_AFTER);
		
		//이상 없을시 장바구니 세션 삭제
		session.removeAttribute("cartList"); //장바구니속 상품 목록 혹은 orderdetailsList
		
		}catch (Exception e) {
			//결제취소 메소드 넣기
			//일단 필요없으니 보류
			new RuntimeException("결제중 오류가 발생하였습니다");
		}
	}
	
	@ResponseBody
	@RequestMapping("/{url}/{url2}")
	public void url() {
		//테스트용 url
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
