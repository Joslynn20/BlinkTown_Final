package web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	@RequestMapping("/main")
	public void ShopMain() {}
	
	@RequestMapping("/{url}")
	public void url() {}
}
