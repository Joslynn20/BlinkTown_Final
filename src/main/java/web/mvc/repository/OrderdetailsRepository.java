package web.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import web.mvc.domain.Orderdetails;

public interface OrderdetailsRepository extends JpaRepository<Orderdetails, Long> , QuerydslPredicateExecutor<Orderdetails> {
	
	/**
	 * 주문 내역 클릭시 해당 주문 코드로 주문 상세 내역 조회 / 페이징처리
	 * : 주문 상세+상품(상품명, 영문명, 대표이미지?)
	 */
	Page<Orderdetails> findByOrdersNo(Long OrdersNo, Pageable pageable);
}
