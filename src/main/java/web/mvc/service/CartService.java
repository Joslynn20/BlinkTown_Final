package web.mvc.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import web.mvc.dto.Cart;
import web.mvc.session.Session;

public interface CartService {

	/**
	 * 장바구니 조회
	 * 
	 * @param sessionId
	 * @return
	 */
	List<Cart> selectCartList(String sessionId);

	/**
	 * 장바구니 넣기
	 * 
	 * @param sessionId
	 * @param cart
	 */
	void insertCart(String sessionId, Cart cart);

	/**
	 * 장바구니 넣기
	 * 
	 * @param session
	 */
	void deleteSession(HttpSession session);

	/**
	 * 장바구니 전체삭제
	 * 
	 * @param sessionId
	 */
	void deleteAllCart(String sessionId);

	/**
	 * 장바구니 개별삭제
	 * 
	 * @param sessionId
	 * @param productCode
	 */
	void deleteCart(String sessionId, String productCode);

}
