package com.mycompany.goldenTime.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.goldenTime.command.EActualListCommand;
import com.mycompany.goldenTime.command.ECongestionCommand;
import com.mycompany.goldenTime.command.ENeedListCommand;

@Controller
public class EDataController {
	
	@Autowired
	EActualListCommand actualListCommand;
	@Autowired
	ENeedListCommand needCommand;
	@Autowired
	ECongestionCommand congestionCommand;
	
	@RequestMapping("/doctor")
	public String doctor() {
		return "doctor";
	}
		
	@RequestMapping("/emrData")
	public Map<String, Object> emrData() {
		System.out.println("emrData mapping, emrData() 메소드 호출");

		 Map<String, Object> jsonData = new HashMap<String, Object>();
		    jsonData.put("actualData", actualListCommand.actualListCommand());
		    jsonData.put("needData", needCommand.needList());
		    jsonData.put("congestData", congestionCommand.congestionList());
		System.out.println("jsonData response");
		return jsonData;
	}
	
	

}
