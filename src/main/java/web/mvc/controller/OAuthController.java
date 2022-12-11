package web.mvc.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import web.mvc.domain.Users;
import web.mvc.dto.KakaoProfile;
import web.mvc.dto.OAuthToken;
import web.mvc.service.UsersService;

//@AllArgsConstructor
@Controller
@RequestMapping("/system")
public class OAuthController {
	@RequestMapping("{url}")
	public void url() {
		System.out.println("url");
	}
	
	
	@Autowired
	private UsersService usersService;

	@RequestMapping("/auth/kakao/callback")
	@ResponseBody
	public String kokoaCallback(@RequestParam String code) throws Exception { 
		System.out.println("111");
		//POST방식으로 key=value 데이터 요엋(카카오 쪽으로)

		//HttpHeader 오브젝트 생성 
		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		//HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "1b05f9fc0b0d4e666e395b0bb60c0f4b");
		params.add("redirect_uri", "https://localhost:8000/auth/kakao/callback");
		params.add("code",code );

		//HttpHeader HttpBody를 하나의 오브젝트로 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenReqest = 
				new HttpEntity<>(params,headers);

		//Http요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response= rt.exchange(
				"https://kauth.kakao.com/oauth/token", //토큰 발급 요청주소
				HttpMethod.POST,
				kakaoTokenReqest,
				String.class	

				);

		//json데이터를 오브젝트에 담는다- Gson, json Simple, objectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;

		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 엑세스 토큰 : " +oauthToken.getAccessToken() );
		////////////////////////////////////////////////////////////////////////	

		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization","Bearer"+oauthToken.getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		//HttpHeader HttpBody를 하나의 오브젝트로 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);

		//Http요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2= rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", //토큰을 통한 사용자 정보조회 요청주소
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class	
				);
		System.out.println("response2.getBody() = "+response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 아이디 : "+kakaoProfile.getId());
		System.out.println("카카오 이메일 : "+kakaoProfile.getKakaoAccount().getEmail());
	
		
		UUID garbagePwd = UUID.randomUUID();
		
		//
		
		
		
		Users kakaoUsers = Users.builder()
				.usersId(kakaoProfile.getKakaoAccount().getEmail()+"_"+kakaoProfile.getId())
				.usersPwd(garbagePwd.toString())
				.usersEmail(kakaoProfile.getKakaoAccount().getEmail())
				.build();
		
		
		
		// 가입자 혹은 비가입자 체크 해서 처리
		Users originusers = usersService.selectbyUserEmail(kakaoUsers.getUsersEmail());

		if(originusers.getUsersEmail() == null) {
			System.out.println("회원가입이 가능합니다");
			
			usersService.insertUser(kakaoUsers); //회원가입 완료
		}
		
		System.out.println("자동 로그인을 진행합니다.");
		
		/**
		// 로그인 처리
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
		*/
		return "/loginForm";
	
	}
}	


