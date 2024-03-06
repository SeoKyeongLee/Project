package com.mycompany.goldenTime.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.CRepository;
import com.mycompany.goldenTime.model.CDataVO;
import com.mycompany.goldenTime.model.CRegressionVO;

@Component
public class CMonthSearchCommand {   
	
	@Autowired
	@Qualifier("CRepository")
	CRepository repository;
	
	public float execute(int year, int month) {
		System.out.println("monthSearchCommand 실행");
		System.out.println("현재 년/월 : " + year + "/" + month);
		CDataVO data = repository.getNationwideData(month);
		CRegressionVO regression = repository.getNationwideRegression();
		
		float RConstant = regression.getConstant();
		System.out.println("회귀식>>");
		System.out.println("상수 : " + RConstant);
		float RYear = regression.getYear();
		System.out.println("년도 상수 : " + RYear);
		float RMonth = regression.getMonth();
		System.out.println("월 상수 : " + RMonth);
		float RPatient_119 = regression.getPatient_119();
		System.out.println("이송건수 상수 : " + RPatient_119);
		float RPatient_ER = regression.getPatient_ER();
		System.out.println("응급실이용건수 상수 : " + RPatient_ER);
		float RTA = regression.getTA();
		System.out.println("교통사고 상수 : " + RTA);
		System.out.println();
		
		int patient_119 = data.getPatient_119();
		System.out.println("이송 : " + patient_119);
		int patient_ER = data.getPatient_ER();
		System.out.println("응급실 : " + patient_ER);
		int TA = data.getTA();
		System.out.println("교통사고: " + TA);
		
		float congestionValue= RConstant + RYear*year + RMonth*month + RPatient_119*patient_119 + RPatient_ER*patient_ER + RTA*TA;
		return congestionValue;
	}
}
