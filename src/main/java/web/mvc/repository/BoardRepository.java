package web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.mvc.domain.Board;
import web.mvc.domain.Like;


public interface BoardRepository extends JpaRepository<Board, Long> {

}
