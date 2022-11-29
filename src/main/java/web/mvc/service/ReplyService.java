package web.mvc.service;

import web.mvc.domain.Reply;

public interface ReplyService {
	
	/**
	 * 게시글에 댓글달기
	 * : 아이디, 댓글내용, 댓글등록일
	 * (댓글번호는 시퀀스로 생성된다)
	 * */
	void insertReply(Reply reply);
		
	/**
	 * 댓글삭제
	 * : 댓글번로(replyNo)를 인수로 받아온다
	 * */
	void deleteReply(Long replyNo);
}
