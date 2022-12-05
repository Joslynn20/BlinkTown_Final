package web.mvc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import web.mvc.dto.Cart;
import web.mvc.session.Session;

@Service
public class CartService {

	private static Set<Session> sessionSet;

	@PostConstruct
	public void init() {
		sessionSet = new HashSet<Session>();
	}

	public List<Cart> selectCartList(String sessionId) {
		Session currentUser = this.getSession(sessionId);

		if (currentUser == null)
			return null;

		return currentUser.getCartList();
	}

	/**
	 * 사용자 찾기
	 */
	private Session getSession(String sessionId) {
		for (Session s : sessionSet) {
			if (s.getSessionId().equals(sessionId))
				return s;
		}
		return null;
	}

	/**
	 * 장바구니 넣기
	 */
	public void insertCart(String sessionId, Cart cart) {
		Session currentUser = this.getSession(sessionId);
		if (currentUser == null) {
			Session newUser = new Session(sessionId, null);
			newUser.getCartList().add(cart);
			sessionSet.add(newUser);
		} else {
			currentUser.getCartList().add(cart);
		}
	}

	/**
	 * 로그아웃
	 * 
	 * @param session
	 */
	public void deleteSession(HttpSession session) {
		sessionSet.remove(this.getSession(session.getId()));
		session.invalidate();
	}

	/**
	 * 장바구니 전체삭제
	 */
	public void deleteAllCart(String sessionId) {
		Session session = this.getSession(sessionId);
		session.getCartList().clear();
	}

	/**
	 * 장바구니 개별삭제
	 */
	public void deleteCart(String sessionId, String productCode) {
		Session session = this.getSession(sessionId);
		List<Cart> cartList = session.getCartList();

		for (Cart cart : cartList) {
			if (cart.getProduct().getProductCode().equals(productCode)) {
				cartList.remove(cart);
				return;
			}
		}
	}

}
