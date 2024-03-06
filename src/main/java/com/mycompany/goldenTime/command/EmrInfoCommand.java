package com.mycompany.goldenTime.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.ERepositoryImpl;
import com.mycompany.goldenTime.model.EmrInfoVO;

@Component
public class EmrInfoCommand {
	
	@Autowired
	ERepositoryImpl repository;
	
	public List<EmrInfoVO> emrInfo(String area) {
		return repository.emrInfo(area);
	}
	
	

}
