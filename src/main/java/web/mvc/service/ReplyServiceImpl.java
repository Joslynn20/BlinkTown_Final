package web.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;

import web.mvc.domain.Reply;
import web.mvc.repository.ReplyRepository;

public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyRepository replyRep;
	
	@Override
	public void insertReply(Reply reply) {
		replyRep.save(reply);
	}

	@Override
	public void deleteReply(Long replyNo) {
		replyRep.deleteById(replyNo);
	}

}