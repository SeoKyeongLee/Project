package com.mycompany.goldenTime.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.model.MemberVO;

@Component
public class MRepository {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public MemberVO getMember(String id) {
		
		String query = "select * from users where id = ?";
		MemberVO member = null;
		try {
			member = jdbc.queryForObject(query, new RowMapper<MemberVO>() {
				@Override
				public MemberVO mapRow(ResultSet rs, int count) throws SQLException {
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setPw(rs.getString("pw"));
					vo.setName(rs.getString("name"));
					vo.setPhone(rs.getString("phone"));
					vo.setEmail(rs.getString("email"));
					vo.setrDate(rs.getTimestamp("rdate"));
					vo.setAddress(rs.getString("address"));
					return vo;
				}
			}, id); 
		} catch(Exception e) {
			member = null;
		}
		
		return member;
	}
	
	public int insertMember(MemberVO member) {
		String query = "insert into users values(?,?,?,?,?,?,?)";
		return jdbc.update(query, member.getId(), member.getPw(), member.getName(), member.getPhone(), member.getEmail(), member.getrDate(), member.getAddress());
	}
	
	public int updateMember(MemberVO member) {
		String query = "Update users set pw=?, email=?, phone=?, address=? where id=?";
		return jdbc.update(query, member.getPw(), member.getEmail(), member.getPhone(), member.getAddress(), member.getId());
	}
}
