package com.mycompany.goldenTime.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.ERepository;
import com.mycompany.goldenTime.model.EmrInfoVO;

@Component
public class EmrInfoCommand {
	
	@Autowired
	ERepository repository;
	
	public List<EmrInfoVO> emrInfo(String area) {
		return repository.emrInfo(area);
	}
	
	

}
