package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepository;
import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BWriteCommand {
	
	@Autowired
	BRepository repository;
	
	public void write(BoardVO bvo) {
		String bName = bvo.getbName();
		String bTitle = bvo.getbTitle();
		String bContent = bvo.getbContent();
		
		repository.write(bName, bTitle, bContent);
	}

}
