package web.mvc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import web.mvc.domain.Orders;
import web.mvc.domain.Users;

public interface OrdersRepository extends JpaRepository<Orders, Long> , QuerydslPredicateExecutor<Orders> {

	/**
	 * 마이페이지-주문내역조회 -> 메인페이지
	 * 출력용 주문10개 조회
	 */
	Page<Orders> findByUsersId(String usersId, Pageable pageable);
	
	/**
	 * 마이페이지-주문상세조회 / 페이징 처리
	 * : 기간 조건별 최근순 주문내역 조회
	 * -> ~부터 ~까지 (입력시 +1로 넣기)
	 */
	Page<Orders> findByOrdersNoAndOrdersDateGreaterThanEqualAndOrdersDateLessThan(String usersId, Date startDate, Date finalDate, Pageable pageable);
	
	/**
	 * 주문-결제
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
	
	/**
	 * 주문 상태 변경(관리자 접근권한)
1) 주문 상태별 조회
2) 결제 완료 상태에서 배송중 으로 상태 변경
	 * 
	 */
}
