package web.mvc.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import web.mvc.domain.Board;
import web.mvc.domain.Users;
import web.mvc.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private final static int PAGE_COUNT=10;
	private final static int BLOCK_COUNT=5;
	
	/**
	 * 전체 검색 페이지
	 * */
	@RequestMapping("/main") 
	public void list(Model model) { 
		List<Board> boardList = boardService.selectAll(); 
		model.addAttribute("mainPageList", boardList);
		
		/**
		 * 아티스트별 게시판
		 * */
		//1) 지수 담기
		Users jisoo=Users.builder().usersId("jisoo").build();
		List<Board> jisooList = boardService.selectByUsers(jisoo);
		//Collections.sort(jisooList, Collections.reverseOrder());
		model.addAttribute("jisooList", jisooList);
		
		//2) 제니 담기
		Users jennie=Users.builder().usersId("jennie").build();
		List<Board> jennieList = boardService.selectByUsers(jennie);
		model.addAttribute("jennieList", jennieList);
		
		//3) 로제 담기
		Users rose=Users.builder().usersId("rose").build();
		List<Board> roseList = boardService.selectByUsers(rose);
		model.addAttribute("roseList", roseList);
		
		//4) 리사 담기
		Users lisa=Users.builder().usersId("lisa").build();
		List<Board> lisaList = boardService.selectByUsers(lisa);
		model.addAttribute("lisaList", lisaList);
	}
	
	
	// 페이징 처리
	/*@RequestMapping("/mainImg")
	public void list(Model model, @RequestParam(defaultValue = "1") int nowPage) {
		
		Pageable page = PageRequest.of((nowPage-1), PAGE_COUNT, Direction.DESC, "boardNo");
		Page<Board> pageList = boardService.selectAll(page);
		System.out.println("pageLis="+pageList);
		
		int temp =(nowPage-1)%BLOCK_COUNT;
		int startPage = nowPage-temp;
		
		model.addAttribute("mainPageList", pageList);	
		
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
		
		//return "/board/main"; //${pageContext.request.contextPath}/
	}*/	
	
	/**
	 * 아티스트별 리스트
	 * */
	/*@ResponseBody
	@RequestMapping("/main/{artist}")
	public void list(Model model, @RequestBody String artist) {//@RequestParam
		Users users=Users.builder().usersId(artist).build();
		List<Board> boardList = boardService.selectByUsers(users);
		model.addAttribute("boardList", boardList);
	}*/
	
	/**
	 * 상세보기
	 * */
	@RequestMapping("/details/{boardNo}")
	public ModelAndView read(@PathVariable Long boardNo) {
		Board board = boardService.selectByBoardNo(boardNo);		
		return new ModelAndView("board/details", "board", board);
	}
	
	
	/**
	 * 게시글 등록폼
	 * */
	@RequestMapping("/insertForm")
	public void write() {}
	
//	/**
//	 * 게시글 등록하기
//	 * */
//	@RequestMapping("/read/{boardNo}")
//	public String insertBoard(Board board) {
//		boardService.insertBoard(board);
//		
//		return "redirect:/board/list";
//	}
	
	/**
	 * 파일등록을 포함한 게시물등록하기
	 * */
	@RequestMapping("/upload")
	public ModelAndView insertBoard(Board board, MultipartFile file, HttpSession session, String boardRegDateTest) {
		//Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//board.setUsers(users);
		
		//String saveDir = session.getServletContext().getRealPath("/resources/save");
		String saveDir = session.getServletContext().getRealPath("/save/boardImg");
		
		String [] s = boardRegDateTest.split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
		board.setBoardRegDate(date);
		
		String originalFileName = file.getOriginalFilename();
		
		try {
			file.transferTo(new File(saveDir+ "/" + originalFileName));
		 
		}catch (Exception e) {
			e.printStackTrace();
		}

		board.setBoardImg(originalFileName);
		
		boardService.insertBoard(board);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("users", board.getUsers());
		mv.addObject("boardTitle", board.getBoardTitle());
		mv.addObject("boardContent", board.getBoardContent());		
		mv.addObject("boardImg", saveDir+ "/" + originalFileName);
		mv.addObject("boardRegDate", board.getBoardRegDate());
		
		mv.addObject("boardLikeNo", board.getBoardLikeNo());		
		mv.setViewName("/board/uploadResult");

		return mv;
	}
		
	
	/**
	 * 게시글 삭제하기
	 * */
	@RequestMapping("/delete")
	public String delete(Long boardNo) {
		boardService.deleteBoard(boardNo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("{url}")
	public void url() {}
	
}
