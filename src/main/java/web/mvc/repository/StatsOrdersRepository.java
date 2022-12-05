package web.mvc.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;

import web.mvc.domain.Users;

public interface StatsOrdersRepository extends JpaRepository<Orders, Long> {

	/**
	 * 전체 매출 조회 & 월별 매출조회
	 */
	@Query(value = "select to_char(o.orders_date, 'yyyy-mm') as month, sum(orderdetails_price * orderdetails_qty) totalprice\r\n"
			+ "from orders o\r\n" + "join orderdetails od\r\n" + "on o.orders_no = od.orders_no\r\n"
			+ "group by to_char(o.orders_date, 'yyyy-mm')", nativeQuery = true)
	StatsInterface findByGetMonth(String Month);

	@Query(value = "select sum(orderdetails_price * orderdetails_qty)totalprice from orders o\r\n"
			+ "join orderdetails od on o.orders_no = od.orders_no", nativeQuery = true)
	StatsInterface findTotalPrice();

	/**
	 * 앨범별
	 */
	@Query(value = "select sum(orderdetails_price ) albumTotalPrice,\r\n"
			+ "sum(orderdetails_qty)albumTotalQty\r\n"
			+ "from orderdetails od\r\n"
			+ "join product p\r\n"
			+ "on od.product_code = p.product_code\r\n"
			+ "left outer join album a\r\n"
			+ "on  p.product_code = a.product_code where p.product_code=?1", nativeQuery = true)
	StatsInterface findAlbumStats(String productCode);
	

}