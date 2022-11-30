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
	 * 아티스트별 게시판검색
	 * : String userId(fk)를 인수로 받아서 검색
	 * 검색하는 userId에 해당되는 것 전부를 List로 보여준다
	 * */
	List<Board> selectById(String userId);
	
	/**
	 * 게시글 등록(아티스트, 관리자가 작성)
	 * : 아이디, 제목, 내용, 이미지(없어도 됨), 작성일, 좋아요 수(기본 0으로)
	 * */
	void insertBoard(Board board);

    /**
     * 게시글 삭제하기
     * : Long boardNo(pk) 게시물번호를 인수로 받음
     * */
    void deleteBoard(Long bno,String password);


    
    
}










