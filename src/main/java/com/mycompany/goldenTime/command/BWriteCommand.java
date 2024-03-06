package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepositoryImpl;
import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BWriteCommand {
	
	@Autowired
	BRepositoryImpl repository;
	
	public void write(BoardVO bvo) {
		String bName = bvo.getbName();
		String bTitle = bvo.getbTitle();
		String bContent = bvo.getbContent();
		
		repository.write(bName, bTitle, bContent);
	}

}
