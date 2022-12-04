package web.mvc.service;

import web.mvc.domain.Likes;
import web.mvc.domain.LikesID;

public interface LikesService {

	/**
	 * 게시글에 좋아요 기능
	 * : 게시물번호와 아이디가 fk
	 * */
	void insertLike(Likes like);
		
	/**
	 * 게시글에 좋아요 취소 기능
	 * : 게시물번호와 아이디를 인수로 받아온다
	 * */
	void deleteLike(Likes like);
	
	/**
	 * 게시글에 대한 좋아요 갯수
	 * @param 게시판 글번호
	 */
	Long selectLikeCount(Long boardNo);
	
	/**
	 * like 있는지 여부 검사
	 */
	Likes selectLike(LikesID like);
	
}
