package web.mvc.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;
import web.mvc.domain.Users;

public interface OrdersService {

	/**
	 * 주문 내역 조회
	 * - 인수 : userId, 조회용 날짜(시작일/마지막일), 페이징처리 정보
	 * - 포함 정보 : 주문테이블, 주문상세테이블
	 * - return : Page객체
	 *  
	 */
	Page<Orders> selectAllOrders(Users usersId, String startDate, String finalDate, Pageable pageable);

	/**
	 * 주문 상세 내역 조회
	 * - 인수 : 주문정보, 페이징처리 정보
	 * - 포함 정보 : 주문 테이블, 주문 상세 테이블, 해당 상품 정보
	 * - return : Page객체
	 */
	Page<Orderdetails> selectAllOrdersdetails(Orders orders, Pageable pageable);
	
	//////////////////////////////////////////////////
	/**
	 * 주문 등록전 상품 및 회원 정보 체크
 	 * 필요 인수 : 회원정보-id,멤버쉽, 상품정보-상품코드, 카테고리, 재고량)
 	 * 
 	 * 넣을 기능들 :
		1) 세션-id, 멤버쉽 : 세션 id에 해당하는 멤버쉽 체크 혹은 세션의 멤버쉽 체크
		2) 주문상품-카테고리 : 주문상품의 카테고리 확인하여 멤버쉽과 유료상품이 매치되는지 체크 / 멤버쉽카드 인지도 체크 (주문전 불가체크:1.유무료/2.카드재구매)
		3) 주문수량-상품재고량 : if 재고량이 1이상일때 / 재고량==0이거나, 재고량-구매수량<0 이면 실패
	 */
	Orders selectCheckBeforeOrders(Users users, Orders ordersProduct);
	
	/**
	 * 주문 등록
	 * 필요 인수 : 회원 정보, 주문 정보, 주문 상세 정보
	 * 등록 정보 : 주문 테이블, 주문 상세 테이블
	 * 
	 */
	void insertOrdersOrderdetails(Users users, Orders ordersProduct);
	
}
