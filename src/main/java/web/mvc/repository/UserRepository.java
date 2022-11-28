package web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import web.mvc.domain.User;


public interface UserRepository extends JpaRepository<User, Long>,QuerydslPredicateExecutor<User> { 
	// JpaRepository<Entity이름, pk타입> QuerydslPredicateExecutor<Entity이름>

	
	@Query("delete from USERS u where u.UsersId >?1")
	@Modifying
	void delete(Long UsersId);
	
	
	//히원 전체검색
	@Query("select *from USERS")
	List<User> selectAll();
	
	
	
	//유료맴버쉽 유무에 따른 회원검색
	@Query("select u from USERS u where u.usersMembership#{#u.usersMembership}  order by u.usersRegDate")
	List<User> selectByUsersMembership(int userMembership);
	

	//////////////////////////////////////////////////////////////////////////////////
//	tongye.selectByDate=select to_char(sum(order_price), 'FM999,999,999,999,999'), sum(order_quan) from order_order where order_time >=to_date(?, 'yymmdd') and order_time<to_date(?+1, 'yymmdd')
//	tongye.selectByMonth=select to_char(sum(order_price), 'FM999,999,999,999,999'), sum(order_quan) from order_order where order_time >=to_date(?, 'yymm') and order_time<to_date(?+1, 'yymm')
	
	
	
}


