package web.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Board;
import web.mvc.domain.Likes;
import web.mvc.domain.LikesID;
import web.mvc.domain.Users;
import web.mvc.repository.BoardRepository;
import web.mvc.repository.LikesRepository;
import web.mvc.repository.UsersRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private JPAQueryFactory queryFactory;

	private final BoardRepository boardRep;

	@Autowired
	private UsersRepository usersRep;

	@Autowired
	private LikesService likeService;

	@Override
	public List<Board> selectAll() {
		return boardRep.findAll();
	}

	@Override
	public Page<Board> selectAll(Pageable pageable) {
		return boardRep.findAll(pageable);
	}

	@Override
	public Board selectBy(Long boardNo) {
		// 조회수 기능을 추가한다면 erd변경하고 여기에 조회수 기능 추가!
		Board board = boardRep.findById(boardNo).orElse(null);
		if (board == null)
			throw new RuntimeException("상세보기 오류입니다.");
		return board;
	}

	
	@Override
	public List<Board> selectByUsers(Users users) {
		List<Board> list = boardRep.findByUsers(users);
		return list;
	}

	@Override
	public void insertBoard(Board board) {
		Board resultBoard = boardRep.save(board);
		//System.out.println("게시글 등록! resultBoard=" + resultBoard);
	}

	@Override
	public void deleteBoard(Long boardNo) {
		Board dbBoard = boardRep.findById(boardNo).orElse(null);
		if (dbBoard == null) {
			throw new RuntimeException("게시글 번호 오류로 삭제 불가능");
		}
		boardRep.deleteById(boardNo);
	}

	@Override
	public int isLike(Likes likes) {
		Likes dbLike = likeService.selectLike(new LikesID(likes.getBoardNo(), likes.getUserId()));
		if (dbLike == null) {
			increaseLikeNo(likes);
		} else {
			decreaseLikeNo(likes);
		}

		return selectBy(likes.getBoardNo()).getBoardLikeNo();

	}

	@Override
	public void increaseLikeNo(Likes likes) {
		likeService.insertLike(likes);
		Board dbBoard = boardRep.findById(likes.getBoardNo()).orElse(null);
		
		if(dbBoard==null) {
			throw new RuntimeException("오류가 발생했습니다.");
		}
		
		dbBoard.setBoardLikeNo(dbBoard.getBoardLikeNo() + 1);
	}

	@Override
	public void decreaseLikeNo(Likes likes) {
		likeService.deleteLike(likes);
		Board dbBoard = boardRep.findById(likes.getBoardNo()).orElse(null);
		dbBoard.setBoardLikeNo(dbBoard.getBoardLikeNo() - 1);
	}

	@Override
	public List<Board> selectByUserId(String userId) {
		List<Board> boardList = new ArrayList();
		List<Likes> likesList = likeService.selectLikesListByUserId(userId);

		for (Likes like : likesList) {
			Board board = this.selectBy(like.getBoardNo());
			boardList.add(board);
		}

		return boardList;
	}


}
