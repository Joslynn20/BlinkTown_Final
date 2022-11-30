package web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import web.mvc.domain.Board;
import web.mvc.domain.Like;


public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	/**
	 * boardNo에 해당하는 조회수 증가
	 * */
	@Query("update Board b set b.readnum=b.readnum+1 where b.boardNo=?1")
	@Modifying
	void updateReadnum(Long boardNo);
	
	/**
	 * 아티스트별 게시판검색
	 * : String userId(fk)를 인수로 받아서 검색
	 * 검색하는 userId에 해당되는 것 전부를 List로 보여준다
	 * */
	@Query("")
	@Modifying
	List<Board> selectById(String userId);
}
