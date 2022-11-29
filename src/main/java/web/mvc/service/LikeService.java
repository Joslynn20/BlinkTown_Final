package web.mvc.service;

import web.mvc.domain.Like;

public interface LikeService {

	/**
	 * 게시글에 좋아요 기능
	 * : 게시물번호와 아이디가 fk
	 * */
	void insertLike(Like like);
		
	/**
	 * 게시글에 좋아요 취소 기능
	 * : 게시물번호와 아이디를 인수로 받아온다
	 * */
	void deleteLike(Long boardNo, String userId);
}
