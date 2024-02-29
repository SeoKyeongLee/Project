package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepository;
import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BReplyViewCommand {
	
	@Autowired
	BRepository repository;
	
	public BoardVO replyView(int bId) {
		return repository.replyView(bId);
	}

}
