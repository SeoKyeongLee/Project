package com.mycompany.goldenTime.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.goldenTime.dao.BRepositoryImpl;
import com.mycompany.goldenTime.model.BoardVO;

@Component
public class BListCommand {
	
	@Autowired
	BRepositoryImpl repository;
	
	public List<BoardVO> boardList(int startIndex, int pageSize) {
		System.out.println("BListCommnad¿« boardList() »£√‚");
		return repository.boardList(startIndex, pageSize);
	}

}
