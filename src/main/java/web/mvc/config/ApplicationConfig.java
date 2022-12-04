package web.mvc.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class ApplicationConfig {

	@PersistenceContext
	private EntityManager em;

	@Bean
	public JPAQueryFactory queryFactory() {
		return new JPAQueryFactory(em);
	}

}
