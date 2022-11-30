package web.mvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Board;
import web.mvc.repository.BoardRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private JPAQueryFactory queryFactory;
	
	private final BoardRepository boardRep;
	
	@Override
	public List<Board> selectAll() {
		return boardRep.findAll();
	}

	@Override
	public Page<Board> selectAll(Pageable pageable) {
		return boardRep.findAll(pageable);
	}

	@Override
	public List<Board> selectById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBoard(Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBoard(Long bno, String password) {
		// TODO Auto-generated method stub

	}

}
