package web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@RequestMapping("/main")
	public void ShopMain() {}
	
	@RequestMapping("/{url}")
	public void url() {}
}
