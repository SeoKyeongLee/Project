package com.mycompany.goldenTime.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.ERepository;
import com.mycompany.goldenTime.model.ERealTimeVO;

@Component
public class ERealTimeCommand {
	
	@Autowired
	ERepository repository;
	
	public List<ERealTimeVO> realTimeList() {
		return repository.realTimeList();
	}
	
	

}
