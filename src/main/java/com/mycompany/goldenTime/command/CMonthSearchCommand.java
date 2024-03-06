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
		System.out.println("monthSearchCommand ����");
		System.out.println("���� ��/�� : " + year + "/" + month);
		CDataVO data = repository.getNationwideData(month);
		CRegressionVO regression = repository.getNationwideRegression();
		
		float RConstant = regression.getConstant();
		System.out.println("ȸ�ͽ�>>");
		System.out.println("��� : " + RConstant);
		float RYear = regression.getYear();
		System.out.println("�⵵ ��� : " + RYear);
		float RMonth = regression.getMonth();
		System.out.println("�� ��� : " + RMonth);
		float RPatient_119 = regression.getPatient_119();
		System.out.println("�̼۰Ǽ� ��� : " + RPatient_119);
		float RPatient_ER = regression.getPatient_ER();
		System.out.println("���޽��̿�Ǽ� ��� : " + RPatient_ER);
		float RTA = regression.getTA();
		System.out.println("������ ��� : " + RTA);
		System.out.println();
		
		int patient_119 = data.getPatient_119();
		System.out.println("�̼� : " + patient_119);
		int patient_ER = data.getPatient_ER();
		System.out.println("���޽� : " + patient_ER);
		int TA = data.getTA();
		System.out.println("������: " + TA);
		
		float congestionValue= RConstant + RYear*year + RMonth*month + RPatient_119*patient_119 + RPatient_ER*patient_ER + RTA*TA;
		return congestionValue;
	}
}
