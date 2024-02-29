package com.mycompany.goldenTime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DController {
	
	@RequestMapping("/person")
	public String person() {
		return "person";
	}

}
