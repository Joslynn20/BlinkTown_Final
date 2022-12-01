package web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import web.mvc.domain.Users;
import web.mvc.domain.Users.UsersBuilder;
import web.mvc.dto.UsersDto;


public interface UserRepository extends JpaRepository<Users, String>,QuerydslPredicateExecutor<Users> { 
	// JpaRepository<Entity이름, pk타입> QuerydslPredicateExecutor<Entity이름>

	
	
	//이메일 중복여부확인 때 사용
	Users findByUsersEmail(String usersEmail);
	
	//전화번호 중복여부확인 때 사용
	Users findByUsersPhone(String usersPhone);
	
	//닉네임 중복여부확인 때 사용
	Users findByUsersNickName(String usersNickName);
	
	//유무료 회원 조회할 때 
	List<Users> findByUsersMemberShip(String usersmemebership);
	
	
	


	
}


