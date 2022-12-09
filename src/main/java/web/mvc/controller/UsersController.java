package web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.mvc.domain.Users;
import web.mvc.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	/** 등록폼 */
	@RequestMapping("/write")
	public void write() {
	}

	/** 등록하기 */
	
	//Id 중복체크
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public boolean idCheck(String usersId) throws Exception {
		System.out.println("id체크시직"+usersId);
		boolean result = usersService.UsersIdCheck(usersId);
		return result;
	}
	
	//이메일 중복체크
	@ResponseBody
	@RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
	public boolean emailCheck(String usersEmail) throws Exception {
		boolean result = usersService.UsersEmailCheck(usersEmail);
		return result;
	}

	//닉네임 중복체크
	@ResponseBody
	@RequestMapping(value = "/nickCheck", method = RequestMethod.POST)
	public boolean nickCheck(String usersNickName) throws Exception {
		boolean result = usersService.UsersNickCheck(usersNickName);
		return result;
	}
	//전화번호 중복체크
	@ResponseBody
	@RequestMapping(value = "/phoneCheck", method = RequestMethod.POST)
	public boolean phoneCheck(String usersPhoneNumber) throws Exception {
		boolean result = usersService.UsersPhoneCheck(usersPhoneNumber);
		return result;
	}
	
	//회원가입하기
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String registerPOST(Users users) throws Exception {
		usersService.insertUser(users);
		return "redirect:/loginForm";
	}
	
	@RequestMapping("/signup")
	public String moveForm() {
		return "/system/signup";
	}

	
///////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 수정하기
 * */
	/**상세 회원정보 조회*/
	@RequestMapping("/read/{usersId}")
	public String selectByUsersId(@PathVariable String usersId) {
		usersService.selectByUsersId(usersId);
		return "users/myPage";
	}



	/** 수정폼 */
	/*@RequestMapping("/updateForm")
	public ModelAndView updateForm(String usersId) {
		Users users = usersService.selectByUsersId(usersId);

		return new ModelAndView("users/update", "users", users);
	}*/
	
	/** 수정 완료하기 */
	@RequestMapping("/update")
	public String updateUsers(Users users) {
		Users dbUser = usersService.updateUsers(users);

		return "redirect:/mypage";
	}

	/** 탈퇴하기 */
	@RequestMapping("/delete")
	private String deleteByUsersId(String usersId) {
		usersService.deleteByUsersId(usersId);
		return "main";
	}

	/** 관리자 - 회원 리스트보기 */
	@RequestMapping("/select../{usersMemberShip}")// /select?usersMemberShip=1
	public ModelAndView read(@PathVariable Integer usersMemberShip) {  //int는 null값을 허용하지 않기 때문에 Integer 사용

		//usersMemberShip == null  -> selectAll
		// usersMemberShip != null -> selectByUsersMemberShip

		List<Users> users = usersService.selectByUsersMemberShip(usersMemberShip);

		return new ModelAndView("board/read", "board", users);
	}

}
