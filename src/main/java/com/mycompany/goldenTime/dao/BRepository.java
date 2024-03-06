package com.mycompany.goldenTime.dao;

import java.util.List;

import com.mycompany.goldenTime.model.BoardVO;

public interface BRepository {
	public List<BoardVO> boardList(int startIndex, int pageSize);
	public int write(String bName, String bTitle, String bContent);
	public BoardVO contentView(int bId);
	public BoardVO replyView(int bId);
	public boolean isAuthor(int bId, String name);
	public int delete(int bId);
	public int reply(BoardVO vo);
	public void replyShape(int group, int step);
	public void upHit(int bId);
	public void modify(BoardVO vo);

}
