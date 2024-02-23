package goldenTime.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import goldenTime.dao.SDao;
import goldenTime.dto.ChartDTO;
import goldenTime.dto.DataDTO;
import goldenTime.dto.RegressionDTO;

public class SRegionSearchCommand implements SCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("RegionSearchCommand 실행");
		int year;
		int month;
		String region;
		
		try {
			BufferedReader reader;
			reader = request.getReader(); // fetchAPI가 보낸 json 파일 읽어올 BufferdReader 생성
			StringBuilder stringBuilder = new StringBuilder(); // fetchAPI가 보낸 json 파일 저장할 stringBuilderb 생성
			String line;

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line); // data read해서 stringBuilder에 저장
			}
      
			String jsonData = stringBuilder.toString(); // stringBuilder에 저장된 내용(char 배열)을 String으로 변환
        
			JSONParser parser = new JSONParser();
        
        	try {
        		JSONObject jsonObject = (JSONObject) parser.parse(jsonData); //json으로 parse한 data를 JSONObject로 변환

        		// "month" 키에 해당하는 값을 읽어오기
        		String monthValue = (String) jsonObject.get("month");
        		year = Integer.parseInt(monthValue.substring(0, 4));
        		System.out.println("지역검색 연도: " + year);
        		month = Integer.parseInt(monthValue.substring(5));
        		System.out.println("지역검색 월 : " + month);
        		region = (String) jsonObject.get("region");
        		System.out.println("지역 : " + region);
        		
        		// DAO call하여 회귀식 data, 독립변수 data, chart data 가져오기 
        		SDao dao = new SDao();
        		RegressionDTO regression = dao.regression(region);
        		DataDTO data = dao.data(region, year, month);
        		
        		// 회귀식 data 변수에 저장
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
        		
        		// 독립변수 data 변수에 저장
        		int patient_119 = data.getPatient_119();
        		System.out.println("이송 : " + patient_119);
        		int patient_ER = data.getPatient_ER();
        		System.out.println("응급실 : " + patient_ER);
        		int TA = data.getTA();
        		System.out.println("교통사고: " + TA);
        		String congestion = null;
        		
				// 혼잡도 구하기
        		float congestionValue= RConstant + RYear*year + RMonth*month + RPatient_119*patient_119 + RPatient_ER*patient_ER + RTA*TA;
        		
        		// 혼잡도 값에 따른 결과 도출
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
        		
        		// 결과data를 response에 binding
        		response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print(congestionValue + ",");
                out.print(congestion + ",");
                out.print(region + ",");
                out.print(year + ",");
                out.print(month + ",");
                out.flush();
        		
        		
        	} catch (Exception e2) {
        		e2.printStackTrace();
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
