package goldenTime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import goldenTime.dto.ChartDTO;
import goldenTime.dto.DataDTO;
import goldenTime.dto.RegressionDTO;

public class SDao {
	
	DataSource dataSource;
	
	public SDao() {
		try {
		Context context = new InitialContext();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public RegressionDTO regression(String region) {
		System.out.println("DAO regression 메소드 호출");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from regression where region=?";
		RegressionDTO regression = new RegressionDTO();
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, region);
			set = pstmt.executeQuery();
			if(set.next() ) {
				float constant = set.getFloat("constant");
				float year = set.getFloat("year");
				float month = set.getFloat("month");
				float patient_119 = set.getFloat("patient_119");
				float patient_ER = set.getFloat("patient_ER");
				float TA = set.getFloat("TA");
				regression.setRegion(region);
				regression.setConstant(constant);
				regression.setYear(year);
				regression.setMonth(month);
				regression.setPatient_119(patient_119);
				regression.setPatient_ER(patient_ER);
				regression.setTA(TA);
			}
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			try {
			connection.close();
			pstmt.close();
			set.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return regression;
		
	}
	
	public DataDTO data(String region, int year, int month) {
		System.out.println("DAO data 메소드 호출");
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from " + region + " where year=2021 and month=?";
		System.out.println("데이터 가져오기 qeury문 : " + query);
		System.out.println();
		DataDTO data = new DataDTO();
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, month);
			set = pstmt.executeQuery();
			if(set.next() ) {
				int patient_119 = set.getInt("patient_119");
				int patient_ER = set.getInt("patient_ER");
				int TA = set.getInt("TA");
				data.setYear(year);
				data.setMonth(month);
				data.setPatient_119(patient_119);
				data.setPatient_ER(patient_ER);
				data.setTA(TA);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				pstmt.close();
				set.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return data;
	}


}
