package web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.mvc.domain.Board;
import web.mvc.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private final static int PAGE_COUNT=10;
	private final static int BLOCK_COUNT=4;
	
	/**
	 * 전체 검색 페이지
	 * */
	/*
	@RequestMapping("/list")
	public void list(Model model, @RequestParam(defaultValue = "1") int nowPage) {	
		List<Board> boardList = boardService.selectAll();
		model.addAttribute("boardList", boardList);
	}*/
	
	// 페이징 처리
	@RequestMapping("/list")
	public void list(Model model, @RequestParam(defaultValue = "1") int nowPage) {
		
		Pageable page = PageRequest.of((nowPage-1), PAGE_COUNT, Direction.DESC, "boardNo");
		Page<Board> pageList = boardService.selectAll(page);
		
		int temp =(nowPage-1)%BLOCK_COUNT;
		int startPage = nowPage-temp;
		
		model.addAttribute("pageList", pageList);	
		model.addAttribute("blockCount", BLOCK_COUNT);
		model.addAttribute("startPage", startPage);
		model.addAttribute("nowPage", nowPage);
		
	}
	
	
	
	
	
	
}
