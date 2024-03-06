package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.MRepositoryImpl;
import com.mycompany.goldenTime.model.MemberVO;

@Component
public class MJoinOkCommand {
	
	@Autowired
	MRepositoryImpl repository;
	
	public int joinOk(MemberVO member) {
		int ri = 0;
		String id = member.getId();
		
		MemberVO checkVo = repository.getMember(id);
		if(checkVo!= null) {
			ri = -1;
		} else {
			ri = repository.insertMember(member);
		}
		
		return ri;
	}

}
