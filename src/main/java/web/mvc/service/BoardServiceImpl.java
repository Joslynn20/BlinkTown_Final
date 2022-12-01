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
	public void insertBoard(Board board) {
		Board resultBoard = boardRep.save(board);
		System.out.println("게시글 등록! resultBoard="+resultBoard);
	}
	
	@Override
	public void deleteBoard(Long boardNo) {
		Board dbBoard = boardRep.findById(boardNo).orElse(null);
		if(dbBoard==null) {
			throw new RuntimeException("게시글 번호 오류로 삭제 불가능");
		}		
		boardRep.deleteById(boardNo);
	}


}
