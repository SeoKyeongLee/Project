package com.mycompany.goldenTime.command;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepository;
import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BContentCommand {
	
	@Autowired
	BRepository repository;
	
	public BoardVO contentView(int bId) {
		return repository.contentView(bId);
	}
	
	public boolean checkIfLoggedIn(HttpSession session) {
		return "yes".equals(session.getAttribute("ValidMem"));
	}
	
}
