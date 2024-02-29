package com.mycompany.goldenTime.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.mycompany.goldenTime.dao.ERepository;
import com.mycompany.goldenTime.model.ENeedVO;

@Component
public class ENeedListCommand {
	
	@Autowired
	ERepository repository;
	
	public List<ENeedVO> needList() {
		return repository.needList();
	}

}
