package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.MRepositoryImpl;
import com.mycompany.goldenTime.model.MemberVO;

@Component
public class MLoginOkCommand{
	
	@Autowired
	MRepositoryImpl repository;

	public int userCheck(String id, String pw) {
		int ri = 0;
		MemberVO member = repository.getMember(id);
		if(member==null) {
			ri = -1;
		}else {
			if(member.getPw().equals(pw)) {
				ri = 1;
			} else {
					ri = 0;
			}
		}
			return ri;
	}

}
