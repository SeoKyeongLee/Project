package goldenTime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import goldenTime.dto.MemberDto;

public class MemberDao {
	DataSource dataSource;
	private static MemberDao instance = new MemberDao();
	
	public MemberDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	//싱글톤 생성
	public static MemberDao getInstance() {
		return instance;
	}
	
	//회원추가
	public int insertMember(MemberDto dto) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into users values(?,?,?,?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.setTimestamp(6, dto.getrDate());
			pstmt.setString(7, dto.getAddress());
			pstmt.executeUpdate();
			ri = 1;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	//Id 확인
	public int confirmId(String id) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from users where id = ?";
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			System.out.println(id);
			if(set.next()) {
				ri = 1;
			} else {
				ri = 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	//pw 확인
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from users where id = ?";
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					ri = 1;
				} else {
					ri = 0;
				}
			} else {
				ri = -1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	//회원 불러오기
	public MemberDto getMember(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from users where id = ?";
		MemberDto dto = null;
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new MemberDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.setEmail(set.getString("email"));
				dto.setPhone(set.getString("phone"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	//회원 정보 수정
	public int updateMember(MemberDto dto) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "Update users set pw=?, email=?, phone=?, address=? where id=?";
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getId());
			ri = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	

}
