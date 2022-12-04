package web.mvc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;

public interface OrderdetailsRepository extends JpaRepository<Orderdetails, Long> , QuerydslPredicateExecutor<Orderdetails> {
	
	/**
	 * 주문 내역 클릭시 해당 주문 코드로 주문 상세 내역 조회 / 페이징처리->우선 안하는 방향이 우선순위
	 * : 주문 상세+상품(상품명, 영문명, 대표이미지?)
	 */
//	Page<Orderdetails> findByOrders(Orders orders, Pageable pageable);
	List<Orderdetails> findByOrders(Orders orders);
}
