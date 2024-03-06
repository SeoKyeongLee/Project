package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.BRepositoryImpl;

@Component
public class BDeleteCommand {
	
	@Autowired
	BRepositoryImpl repository;
	
	public void delete(int bId) {
		repository.delete(bId);
	}
	
	

}
