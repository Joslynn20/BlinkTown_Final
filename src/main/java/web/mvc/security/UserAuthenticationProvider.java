package web.mvc.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Authority;
import web.mvc.domain.Users;
import web.mvc.repository.AuthoritiesRepository;
import web.mvc.repository.UsersRepository;
import web.mvc.service.UsersServiceImpl;

/**
 * 인증을 처리할 클래스
 **/
@Service //id= "memberAuthenticationProvider"
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {
	
	
	private final UsersRepository usesRep;
	private final AuthoritiesRepository authoritiesRep;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 로그인 폼에서 전달된 userName(=id)과 password가 userNamePasswordAuthenticationToken객제로 만들어져서
	 * 인수 Authentication객체에 전달된다.
	 * 전달된 인인수에서userName과 password를 꺼내서 db에 있는 정보와 같은지 확인하고 다르면 인증실패
	 * 만약, 정보가 같으면 인증된 사용자의 정보를 
	 * Authentication의 구현체인 userNamePasswordAuthenticationToken을 생성해서 저장 후 리턴해준다. 
	 * */
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String id = authentication.getName();
		
		 Users users = usesRep.getById(id);
		//평문과 암호화된 비번을 체크
		String pass= (String)authentication.getCredentials().toString();
		
		
		//인증된 사용자의 권한 조회 
		List<Authority> authorityList =  authoritiesRep.findByUsersId(id);
		 //나온 authorityListfmf security의 권한타입(grantedAuthority)에 맞게 형변환
		List<SimpleGrantedAuthority> simpleAuthList = new ArrayList<SimpleGrantedAuthority>();
		for(Authority authority:authorityList) {
			simpleAuthList.add(new SimpleGrantedAuthority(authority.getAuhtorityRole()));
			
		}
		
		//인증성공했으니 인증된 사용자의 정보를 userNamePasswordAuthenticationToken에 저장
		
		UsernamePasswordAuthenticationToken auth = 
				new UsernamePasswordAuthenticationToken(users, null, simpleAuthList);
		
		return auth;
	}

	/**
	 * 인수로 전달된 인증정보가 인증할 수 있는 유효한 객체인지를 판단해주는 메소드
	 * true리턴하면 authenticate 메소드 호출 / false이면 authenticate 메소드 호출XX
	 * */
	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println("MemberAuthenticationProvider의 supports()호출됨. ");
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
