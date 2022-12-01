package web.mvc.controller;

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
	
	private final static int PAGE_COUNT=10;
	private final static int BLOCK_COUNT=10;
	
	//유저 정보 받아오기 : Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	///////////////////////////////////////////////////////
	
	/**
	 * 1-1) 유저 마이페이지 메인 - 최근순 10개정도 주문 내역 조회
	 * 권한 : 일반,멤버쉽 회원
	 * mapping : 일반,멤버쉽/마이페이지 / 메인
	 * return : Page 주문정보
	 * 조건 : inCase==2:마이페이지 메인
	 * 조건 : inCase==3 / 인수로 날짜 (20221130 형식) 받기
	 */
	@RequestMapping("유저/마이페이지/주문페이지")
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
		//기간 조회용 페이지 정보 호출 : (inCase==3) -> 호출이 쉬워서 넣었습니다..
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
	 * 1-2) 유저 마이페이지 - 상세내역 / 주문 내역 클릭시 해당 주문 코드로 주문 내역 조회
	 * : 주문+주문 상세+상품 정보
	 * 권한 : 일반,멤버쉽
	 * mapping : 일반,멤버쉽/마이페이지/상세내역 조회 페이지
	 * return : Page 주문상세정보
	 * 조건 : 인수로 주문번호 or 주문객체 받기
	 */
	@RequestMapping("유저/마이페이지/주문상세페이지/{ordersNo}")
	public void selectAllOrderdetails(Model model, @PathVariable Long ordersNo,
			@RequestParam(defaultValue="1") int nowPage) {
		
		Pageable ordersPage=PageRequest.of(nowPage-1, PAGE_COUNT, Direction.DESC, "orderdetailsNo");
		
		Page<Orderdetails> orderdetailsList=ordersService.selectAllOrderdetails(ordersNo, ordersPage);
		
		//페이징 처리 메소드
		int temp=0;
		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
			temp=(nowPage-1)%BLOCK_COUNT;
		}
		int startPage=nowPage-temp;
		
		model.addAttribute("orderdetailsList", orderdetailsList);
		
		//페이징 처리 위한 정보들 값으로 넣어 전달
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
	}
	
	////////////////////////////////////////////////////
	
	/**
	 * 2-1) 관리자 페이지 - 주문정보 조회 페이지
	 * : 주문 정보
	 * 권한 : 일반,멤버쉽
	 * mapping : 관리자/주문조회페이지/메인 (주문 전체조회 페이지)
	 * return : Page 주문정보
	 * 조건 : inCase==1
	 */
	@RequestMapping("관리자/주문페이지")
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
	 * 2-2) 관리자 페이지 - 주문 내역 클릭시 해당 주문 코드로 주문 내역 조회
	 * : 주문 상세+상품 정보
	 * 권한 : 일반,멤버쉽
	 * mapping : 관리자/주문조회페이지/상세내역 조회 페이지
	 * return : Page 주문상세정보
	 * 조건 : 인수로 주문번호 받기
	 */
	@RequestMapping("관리자/주문상세페이지/{ordersNo}")
	public void selectAllOrderdetailsAdmin(Model model, @PathVariable Long ordersNo,
			@RequestParam(defaultValue="1") int nowPage) {
		
		Pageable ordersPage=PageRequest.of(nowPage-1, PAGE_COUNT, Direction.DESC, "ordersDate");
		
		Page<Orderdetails> orderdetailsList=ordersService.selectAllOrderdetails(ordersNo, ordersPage);

		//페이징 처리 메소드
		int temp=0;
		if (nowPage!=1) { //내가 추가함 0나누기하면 안되니까
			temp=(nowPage-1)%BLOCK_COUNT;
		}
		int startPage=nowPage-temp;
		
		model.addAttribute("orderdetailsList", orderdetailsList);
		
		//페이징 처리 위한 정보들 값으로 넣어 전달
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
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
	 * 3-1) 일반회원 바로 주문하기-폼연결 (권한설정 주의)
	 * 액터 : 일반회원
 	 * mapping : 일반회원/구매/주문폼
	 * 조건 : -
	 */
	@RequestMapping("일반회원/구매/주문폼") /**인수 List들 Hidden으로 넣기(?)*/
	public String usersOrdersForm(List<Product> productList, List<Orderdetails> orderdetailsList /*, Model model*/) {
//		model.addAttribute(productList);
//		model.addAttribute(orderdetailsList);
		return "주문폼";
	}
	
	/**
	 * 3-2) 멤버쉽회원 바로 주문하기-폼연결 (권한설정 주의)
	 * 액터 : 멤버쉽회원
 	 * mapping : 멤버쉽회원/구매 / 주문폼
	 * return : forward : 주문폼으로 연결
	 * 조건 : -
	 */
	@RequestMapping("멤버쉽회원/구매/주문폼")
	public String adminOrdersForm(List<Product> productList, List<Orderdetails> orderdetailsList/*, Model model*/) {
//		model.addAttribute(productList);
//		model.addAttribute(orderdetailsList);
		return "주문폼";
	}
	
	/**
	 * 4) 주문-결제 : 아임포트 API 사용할 예정(통합 결제대행API),
	 * view부분과의 연동 필요하여 확인후 넣을 예정
	 *  
	 *  아래는 정리후 수정할 예정 (서비스 파트와 부정확한 내용 섞여있음)
	 *  
	 *  
	 *  
	 * 조회하여 주문이 넘어왔을때 진행
1) 주문폼 값 전달받기 -> 이때 배송지 값 입력하는 API 사용 (폼 양식에서 사용)
2) 트랜젝션 시작
3) 전달받은 주문 정보로 주문테이블, 주문상세 테이블 C
4-1) 상품 U : if 재고량이 1이상일때 : 재고량 감소 / 재고량-상품수량<0 이면 실패
4-2) 멤버쉽카드일시 :  위 재고량 로직+ 권한생성 + 회원 멤버쉽 1로 update
5) 결제API사용 : 주문코드 전달하여 결제요청
6) 결제 정보 전달받기 : 결제코드
7) 결제정보 테이블에 결제정보 저장
8) 결제성공처리 (결제서비스쪽에 성공값 보내야하나? 보낸다면 보낸 이후 성공처리)
9) 결제 성공 후 장바구니 삭제(세션 삭제) 혹은 세션의 해당 상품 삭제
10) 주문완료 페이지로 이동 (주문 내역 출력되는 페이지 예상)
	 * 
	 */
//	@ResponseBody // 주문하기는 AJAX로 할 예정, (REST API사용) 위에서 RestController선언하면 주석처리
	@RequestMapping("주문/주문하기")
	public String insertOrdersOrderdetails(HttpSession session, Principal principal, Orders orders, List<Product> productList/*, List<Orderdetails> orderdetailsList*/) {
		
		//Orders에 한 번에 들어간다면 인수로 하나만 받기
		List<Orderdetails> orderdetailsList=orders.getOrderdetailsList();//안쓰면 주석처리하긴
		
		//상품 번호로 상품 객체에 들어가는지 확인할 것->안될것같음
		//->현재 orderdetailsList에는 상품 객체로 들어가 있음
		//->Product의 List로 받아서 꺼내서 대입하여 상세주문에 넣기(index가 매치되니까 괜찮을듯)
		for(int i=0; i<productList.size(); i++) {
			orderdetailsList.get(i).setProduct(productList.get(i));
		}
		
		//받은 카트 리스트를 주문에 담기
		orders.setOrderdetailsList(orderdetailsList);
		
		Users users=(Users)principal;
//		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ordersService.insertOrdersOrderdetails(users, orders);
		
		//장바구니 세션 사용하여 insert 완료 후 세션 삭제
		session.removeAttribute("세션속 장바구니 상품 목록");
		
		return "주문완료 페이지";
		
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
