package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepositoryImpl;
import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BBoardModify {
	
	@Autowired
	BRepositoryImpl repository;
	
	public void boardModify(BoardVO vo) {
		
		
	}

}
