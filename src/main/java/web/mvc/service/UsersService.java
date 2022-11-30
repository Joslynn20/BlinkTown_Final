package web.mvc.service;

import java.util.List;

import web.mvc.domain.Users;

public interface UsersService  {
	
	/**
	 * 회원등록
	 * (domain)Users
	 * usersId,usersPwd,usersPwdCheck,usersPhone,usersEmail,
	 * usersNickName,usersMemberShip,usersRegDate
	 * */
	void insertUser(Users users) throws Exception;
	
	/**join 중복체크*/
 
    boolean UsersIdCheck(String UsersId) throws Exception;
    boolean UsersEmailCheck(String UsersEmail) throws Exception;
    boolean UsersNickCheck(String UsersNickName) throws Exception;
    boolean UsersPhoneCheck(String phone_number) throws Exception;
	
	
	
	/**
	 * 회원수정
	 * (domain)Users
	 * usersPwd,usersEmail,usersNickName 수정가능
	 * */
	Users updateUsers(Users users);
	
	/**
	 * 회원삭제
	 * usersId를 인수로 받아 삭제함.
	 * */
	void deleteByUsersId(String usersId);
	
	/**
	 * 회원 전체조회
	 * */
	List<Users> selectAll();
	
	/**
	 * usersId에 해당하는 회원조회
	 * */
	 Users selectByUsersId(String userId);
	
	/**
	 * 멤버쉽 유무에 따른 유/무료 회원 조회
	 * */
	 List<Users> selectByUsersMemberShip(int usersMemberShip);
	
}
