package web.mvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Users;
import web.mvc.repository.UsersRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersServiceImpl implements UsersService {

	private final UsersRepository usersRep;
	
	/*@Autowired
	private JPAQueryFactory queryFactory;*/

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

		Users users = usersRep.findById(usersId).orElse(null);
		if (users != null)
			result = true;

		return result;

	}
	
	// true면 중복
	// 메일 중복체크
	@Override
	public boolean UsersEmailCheck(String UsersEmail) throws Exception {
		boolean result = false;
		
		Users users = usersRep.findByUsersEmail(UsersEmail);
		if (users != null)
			result = true;
		return result;
	}
	// true면 중복
	// 닉네임 중복체크
	@Override
	public boolean UsersNickCheck(String UsersNickName) throws Exception {
		boolean result = false;
		
		Users users = usersRep.findByUsersNickName(UsersNickName);
		if (users != null)
			result = true;
		return result;
	}
	// true면 중복
	// 전화번호 중복체크
	@Override
	public boolean UsersPhoneCheck(String phone_number) throws Exception {
		boolean result = false;
		
		Users users = usersRep.findByUsersPhone(phone_number);
		if (users != null)
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
		usersRep.deleteById(usersId);
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
	//0이면 무료, 1이면 유료, null 이면 전체 회원 검색
	
	public List<Users> selectByUsersMemberShip(Integer usersMemberShip) {
		
		List<Users> usersList=null;
		//usersMemberShip이 null이면 전체조회
		if(usersMemberShip==null) {
			usersList=usersRep.findAll();
		}else{ //null이 아닌 경우
			//membership(0 또는 1에 해당하는 user조회
			usersList=usersRep.findByUsersMemberShip(usersMemberShip);
		}
	
		
		return usersList;
	}
	
	
	 //주문-멤버쉽업데이트
	   @Override
	   public void updateUsersMemberShip(Users users, boolean willMember) {
		  if(willMember==true) users.setUsersMemberShip(1);
		  else users.setUsersMemberShip(0);
	   }


}