package goldenTime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import goldenTime.dto.BoardDto;

public class BoardDao {
	DataSource dataSource;
	private static BoardDao instance = new BoardDao();
	
	public BoardDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//싱글톤 생성
		public static BoardDao getInstance() {
			return instance;
		}
	
	public ArrayList<BoardDto> list(int startIndex, int pageSize){
		ArrayList<BoardDto> dtos = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String query = "SELECT * FROM (SELECT a.*, ROWNUM rnum FROM (SELECT * FROM board ORDER BY bGroup DESC, bStep ASC) a WHERE ROWNUM <= ?) WHERE rnum >= ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startIndex + pageSize);
	        pstmt.setInt(2, startIndex + 1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BoardDto dto = new BoardDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con !=null) con.close(); 
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	//총 레코드(게시물) 수 검색
	public int getTotalRecords() {
        int totalRecords = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            String query = "SELECT COUNT(*) FROM board";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return totalRecords;
    }
	
	public void write(String bName, String bTitle, String bContent) {
		System.out.println("board DAO write() 호출");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(board_seq.nextval,?,?,?,0,board_seq.currval,0,0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			System.out.println("쿼리 날린다");
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public BoardDto contentView(String strId) {
		upHit(strId);
		
		BoardDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String query = "select * from board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strId));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new BoardDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con !=null) con.close(); 
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void modify(String bId, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String query = "update board set bTitle = ?, bContent = ? where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setInt(3, Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();
			System.out.println(bId+", "+bTitle+", "+bContent);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public BoardDto reply_view(String str) {
		System.out.println("board DAO reply_view() 호출");
		BoardDto dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String query = "select * from board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				System.out.println("bGroup:"+bGroup);
				System.out.println("bStep:"+bStep);
				System.out.println("bIndent:"+bIndent);
				
				dto = new BoardDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();				
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		System.out.println("board DAO reply() 호출");
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values(board_seq.nextval,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			System.out.println(bName+", "+bTitle+", "+bContent);
			System.out.println(bGroup+", "+bStep+", "+bIndent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			System.out.println(bGroup+", "+bStep+", "+bIndent);
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private void replyShape(String strGroup, String strStep) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void upHit(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//사용자가 게시물의 작성자인지 확인
	public boolean isAuthor(String bId, String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isAuthor = false;
		try {
			con = dataSource.getConnection();
			String query = "select bName from board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String authorName = rs.getString("bName");
				isAuthor = authorName.equals(name);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return isAuthor;
	}
}
