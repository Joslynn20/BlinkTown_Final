package web.mvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.mvc.domain.Orders;
import web.mvc.service.OrdersService;
import web.mvc.service.StatsService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private StatsService statsService;
	
	/**
	 * 경로 : admin/main.jsp
	 */
	@RequestMapping("/main")
	public void main() {}//main end
	
	/**
	 * 관리자페이지-주문목록 출력 ajax
	 * 위치 : #tab3 / class="tbl-content" id="ordersList" / 이벤트 클릭 : #ordersTab3
	 */
	@ResponseBody
	@RequestMapping("/ordersList")
	public List<Orders> ordersList() {
		System.out.println("ordersList시작");
		//위치 : #tab3 / class="tbl-content" id="ordersList" / 이벤트 클릭 : #ordersTab3
		List<Orders> ordersList=ordersService.selectAllOrdersAdmin();
		System.out.println("ordersList="+ordersList);
		return ordersList;
	}//ordersList end
	
}
