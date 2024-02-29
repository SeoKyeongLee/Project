package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepository;

@Component
public class BDeleteCommand {
	
	@Autowired
	BRepository repository;
	
	public void delete(int bId) {
		repository.delete(bId);
	}
	
	

}
