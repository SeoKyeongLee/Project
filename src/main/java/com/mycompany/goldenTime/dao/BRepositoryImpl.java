package com.mycompany.goldenTime.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BRepositoryImpl implements BRepository{
	
	@Autowired
	JdbcTemplate jdbc;
	
	public List<BoardVO> boardList(int startIndex, int pageSize) {
		System.out.println("DRepository의 boardList() 호출");
		String query = "SELECT * FROM (SELECT a.*, ROWNUM rnum FROM (SELECT * FROM board ORDER BY bGroup DESC, bStep ASC) a WHERE ROWNUM <= ?) WHERE rnum >= ?";
		return jdbc.query(query, new RowMapper<BoardVO>() {
			@Override
			public BoardVO  mapRow(ResultSet rs, int count) throws SQLException {
				BoardVO bvo = new BoardVO();
				bvo.setbId(rs.getInt("bId"));
				bvo.setbName(rs.getString("bName"));
				bvo.setbTitle(rs.getString("bTitle"));
				bvo.setbContent(rs.getString("bContent"));
				bvo.setbDate(rs.getTimestamp("bDate"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbStep(rs.getInt("bStep"));
				bvo.setbIndent(rs.getInt("bIndent"));
				
				return bvo;
			}
		}, startIndex + pageSize, startIndex + 1);
	}
	 
	public int write(String bName, String bTitle, String bContent) {
		System.out.println("DRepository의 write() 호출");
		String query = "insert into board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(board_seq.nextval,?,?,?,0,board_seq.currval,0,0)";
		return jdbc.update(query, bName, bTitle, bContent);
	}
	
	public BoardVO contentView(int bId) {
		System.out.println("DRepository의 boardList() 호출");
		
		upHit(bId);
		
		String query = "select * from board where bId = ?";
		return jdbc.queryForObject(query, new RowMapper<BoardVO>() {
			@Override
			public BoardVO  mapRow(ResultSet rs, int count) throws SQLException {
				BoardVO bvo = new BoardVO();
				bvo.setbId(rs.getInt("bId"));
				bvo.setbName(rs.getString("bName"));
				bvo.setbTitle(rs.getString("bTitle"));
				bvo.setbContent(rs.getString("bContent"));
				bvo.setbDate(rs.getTimestamp("bDate"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbStep(rs.getInt("bStep"));
				bvo.setbIndent(rs.getInt("bIndent"));
				
				return bvo;
			}
		}, bId);
	}
	
	public BoardVO replyView(int bId) {
		String query = "select * from board where bId = ?";
		return jdbc.queryForObject(query, new RowMapper<BoardVO>() {
			@Override
			public BoardVO  mapRow(ResultSet rs, int count) throws SQLException {
				BoardVO bvo = new BoardVO();
				bvo.setbId(rs.getInt("bId"));
				bvo.setbName(rs.getString("bName"));
				bvo.setbTitle(rs.getString("bTitle"));
				bvo.setbContent(rs.getString("bContent"));
				bvo.setbDate(rs.getTimestamp("bDate"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbHit(rs.getInt("bHit"));
				bvo.setbStep(rs.getInt("bStep"));
				bvo.setbIndent(rs.getInt("bIndent"));
				
				return bvo;
			}
		}, bId);
	}
	
	public boolean isAuthor(int bId, String name) {
		String query = "select bName from board where bId = ?";
		String bName = jdbc.queryForObject(query, String.class, bId);
		return name.equals(bName);
	}
	
	public int delete(int bId) {
		String query = "delete from board where bId = ?";
		return jdbc.update(query, bId);
	}
	
	public int reply(BoardVO vo) {
		replyShape(vo.getbGroup(), vo.getbStep());
		String query = "insert into board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values(board_seq.nextval,?,?,?,?,?,?)";
		return jdbc.update(query, vo.getbName(), vo.getbTitle(), vo.getbContent(), vo.getbGroup(), vo.getbStep()+1, vo.getbIndent()+1);
	}
	
	public void replyShape(int group, int step) {
		String query = "update board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
		jdbc.update(query, group, step);
	}
	

	public void upHit(int bId) {
		String query = "update board set bHit = bHit + 1 where bId = ?";
		jdbc.update(query, bId);
	}
	
	public void modify(BoardVO vo) {
		String query = "update board set bTitle = ?, bContent = ? where bId = ?";
		jdbc.update(query, vo.getbTitle(), vo.getbContent(), vo.getbId());
	}
	
	


}
