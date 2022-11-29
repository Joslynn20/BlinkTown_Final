package web.mvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.QUsers;
import web.mvc.domain.Users;
import web.mvc.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersServiceImpl implements UsersService {

	private final UserRepository UsersRep;
	//private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JPAQueryFactory queryFactory;


	@Override
	public void insertUser(Users users) {

		Users dbUsersId = UsersRep.findById(users.getUsersId()).orElse(null);
		if(dbUsersId!=null)
			throw new RuntimeException("존재하는 아이디입니다.");

		if(!users.getUsersPwd().equals(users.getUsersPwdCheck()))
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");


		Users dbUsersEmail = UsersRep.findbyUsersEmail(users.getUsersEmail());
		if(dbUsersEmail != null)
			throw new RuntimeException("존재하는 이메일입니다.");

		Users dbUsersPhone = UsersRep.findbyUsersPhone(users.getUsersPhone());
		if(dbUsersPhone != null)
			throw new RuntimeException("존재하는 전화번호입니다.");

		Users dbUsersNickName = UsersRep.findbyUsersNickName(users.getUsersNickName());
		if(dbUsersNickName != null)
			throw new RuntimeException("존재하는 닉네임입니다.");
		
		 UsersRep.save(users);

	}

	@Override
	public Users updateUsers(Users users) {
		Users dbUsers = UsersRep.findById(users.getUsersId()).orElse(null);
		if(dbUsers==null)
			throw new RuntimeException("존재하지 않는 회원번호로 수정할 수 없습니다.");
		
		if(!users.getUsersPwd().equals(users.getUsersPwdCheck()))
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		
		Users dbUsersEmail = UsersRep.findbyUsersEmail(users.getUsersEmail());
		if(dbUsersEmail != null)
			throw new RuntimeException("존재하는 이메일입니다.");

		Users dbUsersNickName = UsersRep.findbyUsersNickName(users.getUsersNickName());
		if(dbUsersNickName != null)
			throw new RuntimeException("존재하는 닉네임입니다.");
		
		//수정
		dbUsers.setUsersPwd(users.getUsersPwd());
		dbUsers.setUsersEmail(users.getUsersEmail());
		dbUsers.setUsersNickName(users.getUsersNickName());
		
		return dbUsers;
	}

	@Override
	public void deleteByUsersId(String usersId) {
		QUsers users = QUsers.users;
		
		long re = queryFactory.delete(users)
				.where(users.usersId.eq(usersId)).execute();
				
				if(re==0)throw new RuntimeException("삭제할 수 없습니다.");
		
		
	}

	@Override
	public List<Users> selectAll() {
		return UsersRep.findAll();
	}
	
	
	@Override
	public Users selectByUsersId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<Users> selectByUsersMemberShip(int usersMemberShip) {
		// TODO Auto-generated method stub
		return null;
	}

	




}