package com.mycompany.goldenTime.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.model.EActualVO;
import com.mycompany.goldenTime.model.ECongestionVO;
import com.mycompany.goldenTime.model.ENeedVO;

@Component
public class ERepository {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public List<EActualVO> actualList() {
		String query = "select * from actualemnumber";
		return jdbc.query(query, new RowMapper<EActualVO>() {
			@Override
			public EActualVO mapRow(ResultSet rs, int count) throws SQLException {
				EActualVO emNum = new EActualVO();
				emNum.setYear(rs.getInt("YEAR"));
				emNum.setNumOfEm(rs.getInt("NUMOFEM"));
				return emNum;
			}
		});
	}
	
	public List<ENeedVO> needList() {
		String query = "select * from needemnumber";
		return jdbc.query(query, new RowMapper<ENeedVO>() {
			@Override
			public ENeedVO mapRow(ResultSet rs, int count) throws SQLException {
				ENeedVO needNum = new ENeedVO();
				needNum.setYear(rs.getInt("YEAR"));
				needNum.setNumOfEm(rs.getInt("NUMOFEM"));
				return needNum;
			}
		});
	}
	
	public List<ECongestionVO> congestionList() {
		String query = "select * from emcongestion";
		return jdbc.query(query, new RowMapper<ECongestionVO>() {
			@Override
			public ECongestionVO mapRow(ResultSet rs, int count) throws SQLException {
				ECongestionVO emCongestion = new ECongestionVO();
				emCongestion.setYear(rs.getInt("YEAR"));
				emCongestion.setCongestion(rs.getDouble("CONGESTION"));
				return emCongestion;
			}
		});
	}
	

}
