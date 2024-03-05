package com.mycompany.goldenTime.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody Map<String, Object> emrData() {
		System.out.println("emrData mapping, emrData() 메소드 호출");
		 Map<String, Object> jsonData = new HashMap<String, Object>();
		    jsonData.put("actualData", actualListCommand.actualListCommand());
		    jsonData.put("needData", needCommand.needList());
		    jsonData.put("congestData", congestionCommand.congestionList());
		return jsonData;
	}
	
}
