package web.mvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import web.mvc.domain.Likes;
import web.mvc.domain.LikesID;
import web.mvc.domain.QLikes;
import web.mvc.repository.LikesRepository;

@Service
@Transactional
public class LikesServiceImpl implements LikesService {

	@Autowired
	private LikesRepository repository;
	
	@Autowired
	private JPAQueryFactory factory;

	@Override
	public void insertLike(Likes like) {
		repository.save(like);
	}

	@Override
	public void deleteLike(Likes like) {
		repository.deleteById(new LikesID(like.getBoardNo(), like.getUserId()));
	}

	@Override
	public Long selectLikeCount(Long boardNo) {
		return repository.countByBoardNo(boardNo);
	}

	@Override
	public Likes selectLike(LikesID like) {
		return repository.findById(like).orElse(null);
	}

	@Override
	public List<Long> selecLikesByUserId(String userId) {
		QLikes likes = QLikes.likes;
//		factory.selectFrom(likes)
		return null;
	}
	
	

}
