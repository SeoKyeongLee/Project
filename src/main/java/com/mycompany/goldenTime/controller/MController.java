package com.mycompany.goldenTime.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.goldenTime.command.MJoinOkCommand;
import com.mycompany.goldenTime.command.MLoginOkCommand;
import com.mycompany.goldenTime.command.MLogoutCommand;
import com.mycompany.goldenTime.command.MModifyOkCommand;
import com.mycompany.goldenTime.dao.MRepository;
import com.mycompany.goldenTime.model.MemberVO;




@Controller
public class MController {
	
	@Autowired
	MLoginOkCommand loginOkCommand;
	@Autowired
	MJoinOkCommand joinOkCommand;
	@Autowired
	MRepository repository;
	@Autowired
	MLogoutCommand logout;
	@Autowired
	MModifyOkCommand modifyOkCommand;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping("/loginOk")
	public String loginOk(@RequestParam String id, @RequestParam String pw, Model model, HttpSession session) {
		int checkNum = loginOkCommand.userCheck(id, pw);
		if(checkNum==-1) {
			model.addAttribute("errorMessage", "아이디가 존재하지 않습니다.");
		}else if(checkNum==0) {
			model.addAttribute("errorMessage", "비밀번호를 재확인하세요.");
		} else if(checkNum==1) {
			MemberVO member = repository.getMember(id);
			String name = member.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logout.logout(session);
		
		return "login";
	}
	
	@RequestMapping("/joinOk")
	public String joinOk(MemberVO member, Model model, HttpSession session) {
		int checkNum = joinOkCommand.joinOk(member);
		if(checkNum==1) {
			model.addAttribute("joinMessage", "회원 가입이 성공적으로 완료되었습니다.");
			session.setAttribute("id", member.getId());
		} else if(checkNum==-1) {
			model.addAttribute("joinMessage", "이미 존재하는 아이디입니다.");
		} else {
			model.addAttribute("joinMessage", "회원 가입에 실패했습니다.");
		}
		
		return "joinOk";
	}
	
	@RequestMapping("/modify/{id}")
	public String modify(@PathVariable String id, Model model) {
		MemberVO member = repository.getMember(id);
		model.addAttribute("member", member);

		return "modify";
	}
	
	@RequestMapping("/modifyOk")
	public String modifyOk(MemberVO member, Model model) {
		System.out.println("modifyOk() 메소드 호출");
		int checkNum = modifyOkCommand.modifyOk(member);
		if(checkNum==1) {
			model.addAttribute("modifyMessage", "회원정보가 성공적으로 업데이트 되었습니다.");
		} else {
			model.addAttribute("modifyMessage", "회원정보 업데이트에 실패했습니다.");
		}
		System.out.println("modifyOk.jsp로 return");
		
		return "modifyOk";
	}
	
	
	

}
