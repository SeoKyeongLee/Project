package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepositoryImpl;
import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BReplyViewCommand {
	
	@Autowired
	BRepositoryImpl repository;
	
	public BoardVO replyView(int bId) {
		return repository.replyView(bId);
	}

}
