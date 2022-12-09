package web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ErrorSuccessController {
	@RequestMapping("/error/{url}")
	public void errorurl() {}
	
	@RequestMapping("/success/{url}")
	public void successurl() {}
}
