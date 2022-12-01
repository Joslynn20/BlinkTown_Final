package web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import web.mvc.domain.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long>, QuerydslPredicateExecutor<Likes> {

}
