package com.mycompany.goldenTime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EDataController {
	
	@RequestMapping("/doctor")
	public String doctor() {
		return "doctor";
	}
	
	

}
