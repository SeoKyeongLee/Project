package com.mycompany.goldenTime.dao;

import com.mycompany.goldenTime.model.MemberVO;

public interface MRepository {
	
	public MemberVO getMember(String id);
	public int insertMember(MemberVO member);
	public int updateMember(MemberVO member);

}
