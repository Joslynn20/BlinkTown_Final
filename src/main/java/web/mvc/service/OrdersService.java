package web.mvc.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;
import web.mvc.domain.Users;
import web.mvc.dto.Cart;

public interface OrdersService {

	/**
	 * 주문 내역 조회
	 * - 인수 :inCase(메소드 구분용), userId, 조회용 날짜(시작일/마지막일), 페이징처리 정보
	 * - 포함 정보 : 주문테이블, 주문상세테이블
	 * - return : Page객체
	 * 
	 * - inCase==1 : 관리자페이지-main 주문내역(페이징처리)
	 * - inCase==2 : 마이페이지-main 주문내역 10개 출력용 메소드
	 * - inCase==3 : 마이페이지-기간별 주문내역 조회	
	 *  
	 */
	Page<Orders> selectAllOrders(int inCase, Users users, String startDate, String finalDate, Pageable pageable);

	/**
	 * 주문 상세 내역 조회
	 * - 인수 : 주문정보, 페이징처리 정보->우선 안하는 방향으로
	 * - 포함 정보 : 주문 테이블, 주문 상세 테이블, 해당 상품 정보
	 * - return : Page객체->List
	 */
	List<Orderdetails> selectAllOrderdetails(Long ordersNo);
	
	//////////////////////////////////////////////////
	/**
	 * 카트DTO에서 상품 담고 체크하는 메소드
	 * 
	 * 
 	 * 필요 인수 : 회원정보-id,멤버쉽, 상품정보-상품코드, 카테고리, 재고량)
 	 * - 체크 내용 : 1) 상품이 멤버쉽 카드인지 , 2) 재고량 있는지 체크
 	 * - 체크 실패시 RuntimeException처리
 	 * - 멤버쉽 체크는 시큐리티 사용해야돼서 뷰->컨트롤러로 카트 담을 때 분류하는게 좋을듯
 	 * 
 	 * 넣을 기능 상세 내용 :
		1) 주문상품-카테고리 : 주문상품의 카테고리 확인하여 멤버쉽과 유료상품이 매치되는지 체크 / 멤버쉽카드 인지도 체크 (주문전 불가체크:1.유무료/2.카드재구매)
		2) 주문수량-상품재고량 : if 재고량이 1이상일때 / 재고량==0이거나, 재고량-구매수량<0 이면 실패
	 */
//	void selectCheckBeforeOrders(Users users, List<Cart> cartList);
	void selectCheckBeforeOrders(Users users, Orders orders);
	
	/**
	 * 주문 등록
	 * 필요 인수 : 회원 정보, 주문 정보, 주문 상세 정보
	 * 등록 정보 : 주문 테이블, 주문 상세 테이블
	 * 
	 */
//	Orders insertOrdersOrderdetails(Users users, Orders ordersProduct, List<Cart> cartList);
	Orders insertOrdersOrderdetails(Users users, Orders ordersProduct);
	
	/**
	 * 카카오페이 결제후 해당결제내역 보안여부 검증하는 메소드
	 * ->금액으로 금액이 맞는지 체크
	 * 
	 * 1) 검증하기 : 실패하면 RuntimeException 일으킴. 성공시엔 아무 변화 없음
	 * 2-1) 검증 성공시 : 주문 상태 변경->결제완료
	 * 2-2) 검증 실패시 : 삭제 메소드 호출
	 * @param ordersNo
	 * @param verifyAmount
	 */
	void verifyOrders(Long ordersNo, int verifyAmount, String status) throws Exception;
	
	/**
	 * 삭제 메소드 분리
	 * ->멤버쉽카드가 있었으면 회원정보 다시 0으로, 재고량 원복, 주문 및 주문상세 삭제
	 * 1) 검증 실패시 사용
	 * 2) 주문 실패시 사용
	 */
	void deleteOrders(Long ordersNo);
}
