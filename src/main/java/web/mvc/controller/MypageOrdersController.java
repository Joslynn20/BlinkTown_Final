package web.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;
import web.mvc.domain.Users;
import web.mvc.dto.OrdersDTO;
import web.mvc.service.OrdersService;

/**
 * 마이페이지-주문페이지
 * ->차후 통합하는게 좋을듯
 * 
 * 클래스url설정 제외하고 메소드에 직접입력하여 매핑
 * @author 강주형
 *
 */
@Controller
public class MypageOrdersController {

	@Autowired
	private OrdersService ordersService;
	
	/**
	 * 주문목록+상세내역 조회 페이지
	 */
	@RequestMapping("/mypage/orderList")
	public void ordersList(Model model) {
		//유저 테스트용-실제용 구분
		Users users=Users.builder().usersId("user").build();//테스트용
//		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Orders> ordersList=ordersService.selectByUsers(users);
		
		//DTO에 담기
		List<OrdersDTO> ordersDTOList=new ArrayList<OrdersDTO>();
		for(Orders orders:ordersList) {
			int amount=0;
			List<Orderdetails> orderdetailsList=orders.getOrderdetailsList();
			if(orderdetailsList!=null) {
				for(Orderdetails orderdetails : orderdetailsList){
					amount+=orderdetails.getOrderdetailsPrice();
				}//orderdetailsList끝
//				System.out.println("orderdetailsNo="+orderdetailsList.get(0).getOrderdetailsNo());
			}
			System.out.println("amount="+amount);
			OrdersDTO ordersDTO=new OrdersDTO
					(orders.getOrdersNo(), orders.getUsers(), 
					orders.getOrdersReceiverName(), orders.getOrdersReceiverPhone(), 
					orders.getOrdersAddr(), orders.getOrdersZipcode(), 
					orders.getOrdersStatus(), orders.getOrdersDate(), 
					orderdetailsList, amount);
//			System.out.println("orders.getOrdersNo="+orders.getOrdersNo());
			System.out.println("amount2="+amount);
			ordersDTOList.add(ordersDTO);
		}//ordersList반복문 끝
		System.out.println("---------------ordersList반복문끝----------");
		model.addAttribute("ordersList", ordersDTOList);
		
//		if(ordersDTOList.get(0).getOrderdetailsList().get(0)!=null)
//		System.out.println("ordersdetailsList="+ordersDTOList.get(0).getOrderdetailsList().get(0).getOrderdetailsNo());
//		System.out.println("ordersList.amount="+ordersDTOList.get(0).getAmount());
		////////
		//ordersDTO, orderdetailsDTO 
	}
}
