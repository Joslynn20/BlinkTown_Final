package web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import web.mvc.domain.Users;
import web.mvc.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;

	/**등록폼*/
	@RequestMapping("/write")
	public void write() {}
	
	/**등록하기*/
	@RequestMapping("/insert")
	public String insertUser(Users users) {
		usersService.insertUser(users);
		
		return "redirect:/메인?";
		
	}
	
	/**수정폼*/
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(String usersId) {
		Users users = usersService.selectByUsersId(usersId);
		
		return new ModelAndView("users/update","users",users);
	}
	/**수정 완료하기*/
	@RequestMapping("/update")
	public ModelAndView updateUsers(Users users) {
		Users dbUser = usersService.updateUsers(users);
		
		return new ModelAndView("users/read","users",dbUser);
	}
	
	/**삭제하기*/
	@RequestMapping("/delete")
	private String deleteByUsersId(String usersId) {
		usersService.deleteByUsersId(usersId);
		return "redirect:/메인??";
	}
	
	/**상세보기*/
	@RequestMapping("/??")
	public ModelAndView read(@PathVariable int usersMemberShip) {
	
		List<Users> users = usersService.selectByUsersMemberShip(usersMemberShip);// true- 조횟수 증가!! 
		
		return new ModelAndView("board/read","board",users);
	}

}
