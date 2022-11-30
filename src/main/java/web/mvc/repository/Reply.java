package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.mvc.domain.Board;

public interface Reply extends JpaRepository<Board, Long> {

}
