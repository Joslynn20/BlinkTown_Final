package web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.mvc.domain.Album;
import web.mvc.domain.Authority;
import web.mvc.domain.Board;
import web.mvc.domain.Users;

public interface AuthoritiesRepository extends JpaRepository<Authority, Long>{
	
	//insert 하기 (save)
	
	/**
	 *userId에 해당하는 권한 검색.
	 * */
	 List<Authority> findByUsersId(String usersId);


}
