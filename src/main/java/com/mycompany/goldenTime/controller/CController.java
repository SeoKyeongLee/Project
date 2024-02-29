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
	
	
	//default ȥ�⵵(���� ��,��,����) ���ϱ�
	@RequestMapping("/congestion")
	public ModelAndView getMonthCongestion(ModelAndView mav) {
		System.out.println("default ȥ�⵵ getMonthCongestion() �޼ҵ� ȣ��");
		//���� ��, �� ���ϱ�
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);         // �⵵
		int month = c.get(Calendar.MONTH) +1;    // �� 0 ~ 11
		
		float congestionValue = monthSearachCommand.execute(year, month);
		
		String congestion = "";
		
		if(congestionValue>=40) {
			congestion = "�ſ� ȥ��";
		} else if(congestionValue>=30) {
			congestion = "ȥ��";
		} else if(congestionValue>=20) {
			congestion = "����";
		} else {
			congestion = "����";
		}
		
		mav.addObject("congestionValue", congestionValue);
		mav.addObject("congestion", congestion);
		mav.addObject("year", year);
		mav.addObject("month", month);
		mav.setViewName("congestion");
		return mav;
	}
	
	//�� �˻�
	@RequestMapping("/monthSearch")
	public ModelAndView getMonthCongestion(@RequestParam("month") String targetData, ModelAndView mav) {
		System.out.println("�� �˻� getMonthCongestion() �޼ҵ� ȣ��");
		//��, �� ���ϱ�
		int year = Integer.parseInt(targetData.substring(0, 4));
		int month = Integer.parseInt(targetData.substring(5));
		
		float congestionValue = monthSearachCommand.execute(year, month);
		
		String congestion = "";
		
		if(congestionValue>=40) {
			congestion = "�ſ� ȥ��";
		} else if(congestionValue>=30) {
			congestion = "ȥ��";
		} else if(congestionValue>=20) {
			congestion = "����";
		} else {
			congestion = "����";
		}
		
		mav.addObject("congestionValue", congestionValue);
		mav.addObject("congestion", congestion);
		mav.addObject("year", year);
		mav.addObject("month", month);
		mav.setViewName("congestion");
		return mav;
	}
	
	//���� �˻�
	@RequestMapping("/regionSearch/{targetData}")
	public  @ResponseBody Object[] regionSearchcongestion(@PathVariable String targetData) {
		System.out.println("���� �˻� regionSearchcongestion() �޼ҵ� ȣ��");
		//��, �� ���ϱ�
		int year = Integer.parseInt(targetData.substring(0, 4));
		System.out.println("��: " + year);
		int month = Integer.parseInt(targetData.substring(4,6));
		System.out.println("��: " + month);
		String region = targetData.substring(6);
		System.out.println("���� : " + region);
		
		float congestionValue = regionSearchCommand.execute(year, month, region);
		System.out.println("Controller�� ������ ȥ�⵵ : " + congestionValue);
		
		String congestion = "";
		
		if(congestionValue>=40) {
			congestion = "�ſ� ȥ��";
		} else if(congestionValue>=30) {
			congestion = "ȥ��";
		} else if(congestionValue>=20) {
			congestion = "����";
		} else {
			congestion = "����";
		}
		
		System.out.println("Controller�� ȥ�⵵ ��� : " + congestion);
		
		Object[] array = new Object[2];
		array[0] = congestionValue;
		array[1] = congestion;
		
		return array;
	}




}
