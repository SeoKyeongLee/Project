package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepository;
import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BReplyCommand {
	
	@Autowired
	BRepository repository;
	
	public int reply(BoardVO vo) {
		return repository.reply(vo);
	}

}
