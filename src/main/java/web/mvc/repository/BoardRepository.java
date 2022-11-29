package web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.mvc.domain.Board;
import web.mvc.domain.Like;


public interface BoardRepository extends JpaRepository<Board, Long> {
	
	/**
	 * 게시판 전체 조회기능
	 * */
	@Query("select b from Board b order by b.boardNo")
	List<Board> selectAll();
	
	/**
	 * 아티스트별 게시판 조회기능
	 * */
	@Query("select b from Board b where b.userId=?1")
	List<Board> selectById(String userId);
	
	/**
	 * 게시글에 댓글 추가
	 * */	
	@Query("insert into Reply r value(r.boardNo=?1, r.userId=?2, r.replyContent=?3, r.replyRegDate=?4)")
	void insertReply();
	
	/**
	 * 아티스트 게시판 게시글 등록하기
	 * */
	@Query("insert into Board b values(?1, ?2)")
	void insert(Board board);
	
	/**
	 * 아티스트 게시판 게시글 삭제하기
	 * */
	@Query("delete from board b where b.boardNo=?1")
	void deleteBoardNo();


}
