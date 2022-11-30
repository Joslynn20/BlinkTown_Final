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

	private static final String UsersMemeberShip = null;
	private final UserRepository usersRep;
	// private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JPAQueryFactory queryFactory;

	//회원가입 
	@Override
	public void insertUser(Users users) throws Exception {
		usersRep.save(users);

	}

	// true면 중복
	// 아이디 중복체크
	@Override
	public boolean UsersIdCheck(String usersId) throws Exception {
		boolean result = false;

		Users user = usersRep.findById(usersId).orElse(null);
		if (user != null)
			result = true;

		return result;

	}
	
	// true면 중복
	// 메일 중복체크
	@Override
	public boolean UsersEmailCheck(String UsersEmail) throws Exception {
		boolean result = false;
		
		Users user = usersRep.findByUsersEmail(UsersEmail);
		if (user != null)
			result = true;
		return result;
	}
	// true면 중복
	// 닉네임 중복체크
	@Override
	public boolean UsersNickCheck(String UsersNickName) throws Exception {
		boolean result = false;
		
		Users user = usersRep.findByUsersNickName(UsersNickName);
		if (user != null)
			result = true;
		return result;
	}
	// true면 중복
	// 전화번호 중복체크
	@Override
	public boolean UsersPhoneCheck(String phone_number) throws Exception {
		boolean result = false;
		
		Users user = usersRep.findByUsersPhone(phone_number);
		if (user != null)
			result = true;
		return result;
	}
	//회줭정보 수정
	@Override
	public Users updateUsers(Users users) {
		Users dbUsers = usersRep.findById(users.getUsersId()).orElse(null);

		// 수정
		dbUsers.setUsersPwd(users.getUsersPwd());
		dbUsers.setUsersEmail(users.getUsersEmail());
		dbUsers.setUsersNickName(users.getUsersNickName());

		return dbUsers;
	}
	
	//회원탈퇴
	@Override
	public void deleteByUsersId(String usersId) {
		QUsers users = QUsers.users;

		long re = queryFactory.delete(users).where(users.usersId.eq(usersId)).execute();

		if (re == 0)
			throw new RuntimeException("삭제할 수 없습니다.");

	}
	//관리자 회원 전체조회
	@Override
	public List<Users> selectAll() {
		return usersRep.findAll();
	}
	
	//관리자 회원정보 상세보기
	@Override
	public Users selectByUsersId(String usersId) {
		
		Users dbUsers = usersRep.findById(usersId).orElse(null);
		return dbUsers;
	}
	
	//맴쉽  유/무료회원 조회
	//0이면 무료, 1이면 유료
	@Override
	public List<Users> selectByUsersMemberShip(int usersMemberShip) {
	
		return usersRep.findByUsersMemberShip(UsersMemeberShip);
	}

}