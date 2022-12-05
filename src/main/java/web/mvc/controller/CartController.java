package web.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import web.mvc.domain.Product;
import web.mvc.dto.Cart;
import web.mvc.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService service;

	/**
	 * 장바구니 조회
	 */
	@RequestMapping("/select")
	public ModelAndView selectCartList(HttpSession session) {

		String sessionId = session.getId();
		List<Cart> cartList = service.selectCartList(sessionId);

		return new ModelAndView("카트조회페이지", "cartList", cartList);
	}

	/**
	 * 장바구니 넣기
	 */
	@RequestMapping("/insert")
	public void insertCart(HttpSession session, Product product, Integer qty) {
		Cart cart = new Cart(product, (product.getProductPrice() * qty), qty);
		service.insertCart(session.getId(), cart);
	}

	/**
	 * 장바구니 삭제
	 */
	@RequestMapping("/deleteAll")
	public void deleteAllCart(HttpSession session) {
		service.deleteAllCart(session.getId());
	}

	/**
	 * 장바구니 개별삭제
	 */
	@RequestMapping("/delete/{productCode}")
	public void deleteCart(HttpSession session, @PathVariable String productCode) {
		service.deleteCart(session.getId(), productCode);
	}

	/**
	 * 사용자 제거 - 로그아웃 메소드에 추가
	 */
	public void deleteSession(HttpSession session) {
		service.deleteSession(session);
	}

}
