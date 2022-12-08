package web.mvc.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Board;
import web.mvc.domain.Reply;
import web.mvc.domain.Users;
import web.mvc.service.BoardService;
import web.mvc.service.ReplyService;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;	
	private final BoardService boardService;
	
	/**
	 * 댓글 등록하기
	 * ->ajax로 사용
	 * ->text형태로
	 * */
	@RequestMapping("/details/{boardNo}")
	public Reply insertReply(String replyContent, Long boardNo) {
		System.out.println("replyContent="+replyContent);
		System.out.println("boardNo"+boardNo);
		
		Users users=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Board board=boardService.selectByBoardNo(boardNo);
		Reply beforeReply=Reply.builder().board(board).replyContent(replyContent).users(users).build();
		Reply reply=replyService.insertReply(beforeReply);
		return reply;
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
