package goldenTime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import goldenTime.dto.ActualDto;
import goldenTime.dto.CongestionDto;

import goldenTime.dto.NeedDto;


public class EmrDao {
	DataSource dataSource;
	
	public EmrDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ActualDto> actualLiat() {
		ArrayList<ActualDto> dtos = new ArrayList<ActualDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from actualemnumber";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int year = rs.getInt("year");
				int numOfEm = rs.getInt("numOfEm");
				
				ActualDto dto = new ActualDto(year,numOfEm);
				dtos.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<NeedDto> needList() {
		ArrayList<NeedDto> dtos = new ArrayList<NeedDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from needemnumber";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int year = rs.getInt("year");
				int numOfEm = rs.getInt("numOfEm");
				
				NeedDto dto = new NeedDto(year,numOfEm);
				dtos.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<CongestionDto> congestionList() {
		ArrayList<CongestionDto> dtos = new ArrayList<CongestionDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from emcongestion";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int year = rs.getInt("year");
				int congestion = rs.getInt("congestion");
				
				CongestionDto dto = new CongestionDto(year,congestion);
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	

}
