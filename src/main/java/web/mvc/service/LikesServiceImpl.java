package web.mvc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.mvc.domain.Likes;
import web.mvc.domain.LikesID;
import web.mvc.repository.LikesRepository;

@Service
@Transactional
public class LikesServiceImpl implements LikesService {

	@Autowired
	private LikesRepository repository;

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

}
