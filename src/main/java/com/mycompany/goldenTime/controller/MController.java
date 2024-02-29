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
			model.addAttribute("errorMessage", "���̵� �������� �ʽ��ϴ�.");
		}else if(checkNum==0) {
			model.addAttribute("errorMessage", "��й�ȣ�� ��Ȯ���ϼ���.");
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
			model.addAttribute("joinMessage", "ȸ�� ������ ���������� �Ϸ�Ǿ����ϴ�.");
			session.setAttribute("id", member.getId());
		} else if(checkNum==-1) {
			model.addAttribute("joinMessage", "�̹� �����ϴ� ���̵��Դϴ�.");
		} else {
			model.addAttribute("joinMessage", "ȸ�� ���Կ� �����߽��ϴ�.");
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
		System.out.println("modifyOk() �޼ҵ� ȣ��");
		int checkNum = modifyOkCommand.modifyOk(member);
		if(checkNum==1) {
			model.addAttribute("modifyMessage", "ȸ�������� ���������� ������Ʈ �Ǿ����ϴ�.");
		} else {
			model.addAttribute("modifyMessage", "ȸ������ ������Ʈ�� �����߽��ϴ�.");
		}
		System.out.println("modifyOk.jsp�� return");
		
		return "modifyOk";
	}
	
	
	

}
