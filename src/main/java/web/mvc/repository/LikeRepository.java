package web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.mvc.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	/**
	 * 좋아요 등록 기능
	 * */
	@Query("insert into Like l values(l.boardNo=?1, l.userId=?2)")
	void insertLike(Like like);
	
	/**
	 * 좋아요 취소 기능
	 * */
	@Query("delete from Like l where l.userId=?1")
	void deleteLike(String userId);
	
	
	/**
	 * 좋아요 표시한 글 조회하기
	 * */
	@Query("select l from where Like l")
	List<Like> selectLike(Long boardNo);
}
