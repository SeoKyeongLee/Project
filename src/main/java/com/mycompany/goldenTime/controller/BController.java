package com.mycompany.goldenTime.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.goldenTime.command.BContentCommand;
import com.mycompany.goldenTime.command.BDeleteCommand;
import com.mycompany.goldenTime.command.BListCommand;
import com.mycompany.goldenTime.command.BReplyCommand;
import com.mycompany.goldenTime.command.BReplyViewCommand;
import com.mycompany.goldenTime.command.BWriteCommand;
import com.mycompany.goldenTime.dao.BRepositoryImpl;
import com.mycompany.goldenTime.model.BoardVO;

@Controller
public class BController {
	
	@Autowired
	BListCommand listCommand;
	@Autowired
	BWriteCommand writeCommand;
	@Autowired
	BContentCommand contentCommand;
	@Autowired
	BRepositoryImpl repository;
	@Autowired
	BReplyViewCommand replyViewCommand;
	@Autowired
	BReplyCommand replyCommand;
	@Autowired
	BDeleteCommand deleteCommnad;
	
	@RequestMapping("/list") 
	public ModelAndView boardList(@RequestParam(value = "page", required = false) String pageParam, ModelAndView mav) {
		int currentPage = 1;
		
		if(pageParam != null && !pageParam.isEmpty()) {
			currentPage = Integer.parseInt(pageParam);
		}
		int pageSize = 7;
		int startIndex = (currentPage -1) * pageSize;
		
		mav.addObject("list", listCommand.boardList(startIndex, pageSize));
		mav.setViewName("list");
		
		return mav;
	}
	
	@RequestMapping("/write_view") 
	public String writeView() {
		return "write_view";
	}
	
	@RequestMapping("/write") 
	public String write(BoardVO bvo) {
		writeCommand.write(bvo);
		return "redirect:list";
	}
	
	@RequestMapping("/content_view/{dto.bId}") 
	public ModelAndView boardList(@PathVariable(value = "dto.bId") int bId, ModelAndView mav, HttpSession session) {
		mav.addObject("content_view", contentCommand.contentView(bId));
		
		// 사용자가 로그인 상태인지 확인
		boolean isLoggedIn = contentCommand.checkIfLoggedIn(session);
		
		// 사용자가 로그인한 경우, 로그인한 사용자가 글쓴이인지 확인
		if (isLoggedIn) {
			boolean isAuthor = repository.isAuthor(bId, (String)session.getAttribute("name"));		
			mav.addObject("isAuthor", isAuthor);
		}
		
		mav.setViewName("content_view");
		
		return mav;
	}
	
	@RequestMapping("/delete/{content_view.bId}")
	public String delete(@PathVariable(value = "content_view.bId") int bId) {
		deleteCommnad.delete(bId);
		return "redirect:/list";
	}
	
	@RequestMapping("/reply_view/{content_view.bId}")
	public ModelAndView replyView(@PathVariable(value = "content_view.bId") int bId, ModelAndView mav) {
		mav.addObject("reply_view", replyViewCommand.replyView(bId));
		mav.setViewName("reply_view");
		return mav;
	}
	
	@RequestMapping("/reply/{reply_view.bId}")
	public String reply(BoardVO vo,@PathVariable(value = "reply_view.bId") int bId) {
		replyCommand.reply(vo);
		return "redirect:/list";
	}
	
	@RequestMapping("/boardModify") 
	public String boardModify(BoardVO bvo) {
		repository.modify(bvo);
		return "redirect:list";
	}
	

	
}
