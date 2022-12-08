package web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import web.mvc.domain.Orders;
import web.mvc.service.OrdersService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private OrdersService ordersService;
	
	/**
	 * 경로 : admin/main.jsp
	 * 페이지가 하나여서 이곳에 릴레이로 넣어야될듯 싶습니다..
	 * (주형:주문목록 완료)
	 */
	@RequestMapping("/main")
	public void mainTotal(Model model) {
		//주문목록 기능
		//위치 : #tab3 / class="tbl-content" id="ordersList" 
		List<Orders> ordersList=ordersService.selectAllOrdersAdmin();
		model.addAttribute("ordersList", ordersList);
		////////////////////////////////////////////////////
		
	}
}
