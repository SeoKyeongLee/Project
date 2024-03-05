package com.mycompany.goldenTime.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.goldenTime.command.ERealTimeCommand;
import com.mycompany.goldenTime.command.EmrInfoCommand;
import com.mycompany.goldenTime.command.EmrRecommandCommand;
import com.mycompany.goldenTime.model.EDistanceToERVO;
import com.mycompany.goldenTime.model.ERealTimeVO;
import com.mycompany.goldenTime.model.EmrInfoVO;

@Controller
public class EStatusController {
	
	@Autowired
	ERealTimeCommand realTimeCommand;
	@Autowired
	EmrInfoCommand emrInfoCommand;
	@Autowired
	EmrRecommandCommand emrRecommandCommand;
	
	@RequestMapping("/emrStatus")
	public String lemrStatus() {
		return "emrStatus";
	}
	
	@RequestMapping("/realTimeEmr")
	public @ResponseBody List<ERealTimeVO> realTimeEmr() {
		return realTimeCommand.realTimeList();
	}
	
	@RequestMapping("/emrInfo")
	public @ResponseBody List<EmrInfoVO> emrInfo(@RequestParam("area") String area) {
		List<EmrInfoVO> result = emrInfoCommand.emrInfo(area);
		System.out.println(area + " response");
		return result;
	}
	
	@RequestMapping("/distanceToER")
	public @ResponseBody List<Map<String, Object>> emrRecommand(@RequestBody List<EDistanceToERVO> distancesToER) {
		return emrRecommandCommand.emrRecommand(distancesToER);
	}
	
}
