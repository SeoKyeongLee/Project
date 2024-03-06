package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.MRepositoryImpl;
import com.mycompany.goldenTime.model.MemberVO;

@Component
public class MModifyOkCommand {
	
	@Autowired
	MRepositoryImpl repository;
	
	public int modifyOk(MemberVO member) {
		return repository.updateMember(member);
	}

}
