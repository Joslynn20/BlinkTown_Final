package web.mvc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.mvc.domain.Board;


public interface BoardService {
	/**
	 * 전체 게시판검색
	 * (게시물번호, 제목, 조회수 보여짐)
	 * */
	List<Board> selectAll();
	
	/**
	 * 전체검색 - Page처리
	 * */
	Page<Board> selectAll(Pageable pageable);
    
	
	/**
	 * 게시글 등록(아티스트, 관리자가 작성)
	 * : 아이디, 제목, 내용, 이미지(없어도 됨), 작성일, 좋아요 수(기본 0으로)
	 * */
	void insertBoard(Board board);

    /**
     * 게시글 삭제하기
     * : Long boardNo(pk) 게시물번호를 인수로 받음
     * */
    void deleteBoard(Long boardNo);


    
    
}










