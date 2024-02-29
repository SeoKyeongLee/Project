package com.mycompany.goldenTime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EStatusController {
	
	@RequestMapping("/emrStatus")
	public String lemrStatus() {
		return "emrStatus";
	}

}
