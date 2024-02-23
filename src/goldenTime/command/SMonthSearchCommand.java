package goldenTime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.SDao;
import goldenTime.dto.DataDTO;
import goldenTime.dto.RegressionDTO;

public class SMonthSearchCommand implements SCommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("monthSearchCommand 실행");
		int year = Integer.parseInt(request.getParameter("month").substring(0, 4));
		int month = Integer.parseInt(request.getParameter("month").substring(5));
		
		SDao dao = new SDao();
		RegressionDTO regression = dao.regression("nationwide");
		DataDTO data = dao.data("nationwide", year, month);
		
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
		
		System.out.println("data>>");
		System.out.println("년도: " + year);
		System.out.println("월: " + month);
		
		int patient_119 = data.getPatient_119();
		System.out.println("이송 : " + patient_119);
		int patient_ER = data.getPatient_ER();
		System.out.println("응급실 : " + patient_ER);
		int TA = data.getTA();
		System.out.println("교통사고: " + TA);
		String congestion = null;
		
		float congestionValue= RConstant + RYear*year + RMonth*month + RPatient_119*patient_119 + RPatient_ER*patient_ER + RTA*TA;
		
		if(congestionValue>=40) {
			congestion = "매우 혼잡";
		} else if(congestionValue>=30) {
			congestion = "혼잡";
		} else if(congestionValue>=20) {
			congestion = "보통";
		} else {
			congestion = "적정";
		}
		System.out.println();
		System.out.println("결과>>");
		System.out.println("검색 시점: " + year + "-" + month + " / 혼잡도: " + congestionValue);
		
		request.setAttribute("congestion", congestion);
		request.setAttribute("congestionValue", congestionValue);
		request.setAttribute("region", "전국");
		request.setAttribute("year", year);
		request.setAttribute("month", month);	
	}

}
