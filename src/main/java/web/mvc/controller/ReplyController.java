package web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Board;
import web.mvc.domain.Reply;
import web.mvc.service.BoardService;
import web.mvc.service.ReplyService;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;	
	private final BoardService boardService;
	
	/**
	 * 댓글 작성폼
	 * */
	@RequestMapping("/writeForm")
	public String writeForm(Long boardNo, Model model) {
		model.addAttribute("boardNo", boardNo);
		return "/reply/write";
	}
	
	/**
	 * 댓글 등록하기
	 * */
	@RequestMapping("/insert")
	public String insertReply(Reply reply, Long boardNo) {
		//Board board = new Board();//1개짜리 생성자만들기
		Board board=Board.builder().boardNo(boardNo).build();
		reply.setBoard(board);
		
		replyService.insertReply(reply);
		
		return "redirect:/board/read/"+boardNo+"?flag=1";
	}
	
	/**
	 * 댓글 삭제하기
	 * */
	@RequestMapping("/delete/{replyNo}/{boardNo}")
	public String deleteReply(@PathVariable Long replyNo, @PathVariable Long boardNo) {
		replyService.deleteReply(replyNo);
		
		return "redirect:/board/read/"+boardNo+"?flag=1";
	}
		
	
}
