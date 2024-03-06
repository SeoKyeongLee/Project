package com.mycompany.goldenTime.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.model.CDataVO;
import com.mycompany.goldenTime.model.CRegressionVO;

@Component
public class CRepositoryImpl implements CRepository{
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public CDataVO getNationwideData(int month) {
		String query = "select * from nationwide where year=2021 and month=?";
		return jdbc.queryForObject(query, new RowMapper<CDataVO>() {
			@Override
			public CDataVO mapRow(ResultSet rs, int count) throws SQLException {
				CDataVO data = new CDataVO();
				data.setYear(rs.getInt("year"));
				data.setMonth(rs.getInt("month"));
				data.setPatient_119(rs.getInt("patient_119"));
				data.setPatient_ER(rs.getInt("patient_ER"));
				data.setTA(rs.getInt("TA"));
				
				return data;
			}
		}, month); 
	}
	
	@Override
	public CRegressionVO getNationwideRegression() {
		String query = "select * from regression where region='nationwide'";
		return jdbc.queryForObject(query, new RowMapper<CRegressionVO>() {
			@Override
			public CRegressionVO mapRow(ResultSet rs, int count) throws SQLException {
				CRegressionVO regression = new CRegressionVO();
				regression.setRegion("nationwide");
				regression.setConstant(rs.getFloat("constant"));
				regression.setYear(rs.getFloat("year"));
				regression.setMonth(rs.getFloat("month"));
				regression.setPatient_119(rs.getFloat("patient_119"));
				regression.setPatient_ER(rs.getFloat("patient_ER"));
				regression.setTA(rs.getInt("TA"));
				
				return regression;
			}
		});
		
	}
	
	public CDataVO getRegionData(Map<String, Object> parameter) {
		String region = (String) parameter.get("region");
		Object month= parameter.get("month");
		String query = "select * from " + region + " where year=2021 and month=?";
		return jdbc.queryForObject(query, new RowMapper<CDataVO>() {
			@Override
			public CDataVO mapRow(ResultSet rs, int count) throws SQLException {
				CDataVO data = new CDataVO();
				data.setYear(rs.getInt("year"));
				data.setMonth(rs.getInt("month"));
				data.setPatient_119(rs.getInt("patient_119"));
				data.setPatient_ER(rs.getInt("patient_ER"));
				data.setTA(rs.getInt("TA"));
				
				return data;
			}
		}, month); 
	}
	
	public CRegressionVO getRegionRegression(String region) {
		String query = "select * from regression where region=?";
		return jdbc.queryForObject(query, new RowMapper<CRegressionVO>() {
			@Override
			public CRegressionVO mapRow(ResultSet rs, int count) throws SQLException {
				CRegressionVO regression = new CRegressionVO();
				regression.setRegion("nationwide");
				regression.setConstant(rs.getFloat("constant"));
				regression.setYear(rs.getFloat("year"));
				regression.setMonth(rs.getFloat("month"));
				regression.setPatient_119(rs.getFloat("patient_119"));
				regression.setPatient_ER(rs.getFloat("patient_ER"));
				regression.setTA(rs.getInt("TA"));
				
				return regression;
			}
		}, region);
		
	}
	
}
