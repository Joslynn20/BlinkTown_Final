package web.mvc.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

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
	
	/**
	 * 좋아요 표시한 글 모아보기
	 * : 게시물번호
	 * */
	List<Like> selectAll(Long boardNo);
}
