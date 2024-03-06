package com.mycompany.goldenTime.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.mycompany.goldenTime.dao.ERepositoryImpl;
import com.mycompany.goldenTime.model.EActualVO;

@Component
public class EActualListCommand {
	
	@Autowired
	ERepositoryImpl repository;
	
	public List<EActualVO> actualListCommand() {
		// TODO Auto-generated constructor stub
		return repository.actualList();
	}


}
