package com.mycompany.goldenTime.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.ERepositoryImpl;
import com.mycompany.goldenTime.model.ECongestionVO;

@Component
public class ECongestionCommand {
	
	@Autowired
	ERepositoryImpl repository;
	
	public List<ECongestionVO> congestionList() {
		return repository.congestionList();
	}
	

}
