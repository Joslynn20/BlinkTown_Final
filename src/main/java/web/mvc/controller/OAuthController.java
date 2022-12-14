package web.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import web.mvc.domain.Authority;
import web.mvc.domain.Users;
import web.mvc.dto.KakaoProfile;
import web.mvc.dto.OAuthToken;
import web.mvc.repository.AuthoritiesRepository;
import web.mvc.service.UsersService;
import web.mvc.util.RoleConstants;

//@AllArgsConstructor
@Controller
@RequestMapping("/system")
public class OAuthController {
   @RequestMapping("/{url}")
   public void url() {
      System.out.println("url");
   }
   
   @Value("${cos.key}")
   private String cosKey;
   
   @Autowired
   private AuthenticationManager authenticationManager;
   
   @Autowired
   private UsersService usersService;
   @Autowired
   private AuthoritiesRepository authoritiesRep;
   @Autowired
   private PasswordEncoder encoderPwd;
   
   
   @RequestMapping("/auth/kakao/callback")
   //@ResponseBody
   public String kokoaCallback(@RequestParam String code) throws Exception { 
      System.out.println("111 code1111 = " + code);
      //POST???????????? key=value ????????? ??????(????????? ?????????)

      //HttpHeader ???????????? ?????? 
      RestTemplate rt = new RestTemplate();

      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

      //HttpBody ???????????? ??????
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("grant_type", "authorization_code");
      params.add("client_id", "1b05f9fc0b0d4e666e395b0bb60c0f4b");
      params.add("redirect_uri", "http://localhost:8000/system/auth/kakao/callback");
      params.add("code",code );

      //HttpHeader HttpBody??? ????????? ??????????????? ??????
      HttpEntity<MultiValueMap<String, String>> kakaoTokenReqest = 
            new HttpEntity<>(params,headers);

      //Http???????????? - Post???????????? - ????????? response ????????? ?????? ??????.
      ResponseEntity<String> response= rt.exchange(
            "https://kauth.kakao.com/oauth/token", //?????? ?????? ????????????
            HttpMethod.POST,
            kakaoTokenReqest,
            String.class   

            );
      
      System.out.println("???????????? ??????"+response);

      //json???????????? ??????????????? ?????????- Gson, json Simple, objectMapper
      ObjectMapper objectMapper = new ObjectMapper();
      OAuthToken oauthToken = null;

      try {
         oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
      } catch (JsonMappingException e) {
         e.printStackTrace();
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }
      
      System.out.println("????????? ????????? ?????? : " +oauthToken.getAccess_token() );
      
      /*return response.getBody();*/
      ////////////////////////////////////////////////////////////////////////   

      RestTemplate rt2 = new RestTemplate();

      HttpHeaders headers2 = new HttpHeaders();
      headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
      headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

      //HttpHeader HttpBody??? ????????? ??????????????? ??????
      HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
            new HttpEntity<>(headers2);

      //Http???????????? - Post???????????? - ????????? response ????????? ?????? ??????.
      ResponseEntity<String> response2= rt2.exchange(
            "https://kapi.kakao.com/v2/user/me", //????????? ?????? ????????? ???????????? ????????????
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
      
      
      System.out.println("????????? ????????? 22: "+kakaoProfile.getId());
      System.out.println("????????? ????????? 22: "+kakaoProfile.getKakao_account().getEmail());
      //System.out.println("????????? ????????? : "+kakaoProfile.getKakao_account().getProfile().getNickname());

      UUID garbagePwd = UUID.randomUUID();
      System.out.println("cosKey = " + cosKey);
      
      
      Users kakaoUsers = Users.builder()
            .usersId(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
            .usersPwd(cosKey)
            .usersEmail(kakaoProfile.getKakao_account().getEmail())
            .build();
   
      
      // ????????? ?????? ???????????? ?????? ?????? ??????
      Users originusers = usersService.selectbyUserEmail(kakaoUsers.getUsersEmail());
         System.out.println("originusers = " + originusers);
         
      if(originusers.getUsersEmail() == null) {
         System.out.println("??????????????? ???????????????2222");
          // ROLE = ?????????
         originusers=  usersService.insertUser(kakaoUsers); //???????????? ??????
      }
      
       System.out.println("authenticationManager = " + authenticationManager);
      
      // ????????? ??????
         /*   Authentication authentication = 
                  authenticationManager
                  .authenticate(new UsernamePasswordAuthenticationToken
                        (kakaoUsers.getUsersId() ,"cos1234"));*/
       
      /* List<SimpleGrantedAuthority> simpleAuthList = new ArrayList<SimpleGrantedAuthority>();
       List<Authority> authorityList = authoritiesRep.findByUsersId(originusers.getUsersId());
       for(Authority authority : authorityList) {
         simpleAuthList.add(new SimpleGrantedAuthority(authority.getAuhtorityRole()));
      }*/
       
       System.out.println("1111111");
       
     Authentication authentication = authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(kakaoUsers.getUsersId() ,cosKey ));
      System.out.println("222222222");
      
      SecurityContext context  = SecurityContextHolder.getContext();
      System.out.println("----------------");      
      context.setAuthentication(authentication); 
            
      System.out.println("?????? ???????????? ???????????????11111111111 ??????.....");
   
      
      System.out.println("?????????");
      return "redirect:/";
   
      
   
   }
}   

