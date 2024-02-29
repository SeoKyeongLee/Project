package com.mycompany.goldenTime.command;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class MLogoutCommand {
	
	public void logout(HttpSession session) {
		session.invalidate();
	}

}
