package com.mycompany.goldenTime.controller;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.goldenTime.command.CMonthSearchCommand;
import com.mycompany.goldenTime.command.CRegionSearchCommand;

@Controller
public class CController {
	
	@Autowired
	CMonthSearchCommand monthSearachCommand;
	@Autowired
	CRegionSearchCommand regionSearchCommand;
	
	
	//default 혼잡도(현재 년,월,전국) 구하기
	@RequestMapping("/congestion")
	public ModelAndView getMonthCongestion(ModelAndView mav) {
		System.out.println("default 혼잡도 getMonthCongestion() 메소드 호출");
		//현재 년, 월 구하기
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);         // 년도
		int month = c.get(Calendar.MONTH) +1;    // 월 0 ~ 11
		
		float congestionValue = monthSearachCommand.execute(year, month);
		
		String congestion = "";
		
		if(congestionValue>=40) {
			congestion = "매우 혼잡";
		} else if(congestionValue>=30) {
			congestion = "혼잡";
		} else if(congestionValue>=20) {
			congestion = "보통";
		} else {
			congestion = "적정";
		}
		
		mav.addObject("congestionValue", congestionValue);
		mav.addObject("congestion", congestion);
		mav.addObject("year", year);
		mav.addObject("month", month);
		mav.setViewName("congestion");
		return mav;
	}
	
	//월 검색
	@RequestMapping("/monthSearch")
	public ModelAndView getMonthCongestion(@RequestParam("month") String targetData, ModelAndView mav) {
		System.out.println("월 검색 getMonthCongestion() 메소드 호출");
		//년, 월 구하기
		int year = Integer.parseInt(targetData.substring(0, 4));
		int month = Integer.parseInt(targetData.substring(5));
		
		float congestionValue = monthSearachCommand.execute(year, month);
		
		String congestion = "";
		
		if(congestionValue>=40) {
			congestion = "매우 혼잡";
		} else if(congestionValue>=30) {
			congestion = "혼잡";
		} else if(congestionValue>=20) {
			congestion = "보통";
		} else {
			congestion = "적정";
		}
		
		mav.addObject("congestionValue", congestionValue);
		mav.addObject("congestion", congestion);
		mav.addObject("year", year);
		mav.addObject("month", month);
		mav.setViewName("congestion");
		return mav;
	}
	
	//지역 검색
	@RequestMapping("/regionSearch/{targetData}")
	public  @ResponseBody Object[] regionSearchcongestion(@PathVariable String targetData) {
		System.out.println("지역 검색 regionSearchcongestion() 메소드 호출");
		//년, 월 구하기
		int year = Integer.parseInt(targetData.substring(0, 4));
		System.out.println("년: " + year);
		int month = Integer.parseInt(targetData.substring(4,6));
		System.out.println("월: " + month);
		String region = targetData.substring(6);
		System.out.println("지역 : " + region);
		
		float congestionValue = regionSearchCommand.execute(year, month, region);
		System.out.println("Controller로 가져온 혼잡도 : " + congestionValue);
		
		String congestion = "";
		
		if(congestionValue>=40) {
			congestion = "매우 혼잡";
		} else if(congestionValue>=30) {
			congestion = "혼잡";
		} else if(congestionValue>=20) {
			congestion = "보통";
		} else {
			congestion = "적정";
		}
		
		System.out.println("Controller의 혼잡도 결과 : " + congestion);
		
		Object[] array = new Object[2];
		array[0] = congestionValue;
		array[1] = congestion;
		
		return array;
	}




}
