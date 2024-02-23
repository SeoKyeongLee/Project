package goldenTime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import goldenTime.dto.DDto;

public class DDao {
	DataSource dataSource;

	public DDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<DDto> list() {
		System.out.println("list메서드 실행 ");
		ArrayList<DDto> dtos = new ArrayList<DDto>();
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		
		try {
			connection =dataSource.getConnection();
			String query = "select region,man,woman,total from doctorseoul";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			System.out.println("resultSet : "+resultSet);
			
			while(resultSet.next()) {
				System.out.println( "region :" + resultSet.getString("region"));
				
				
				String region =resultSet.getString("region");
				int woman = resultSet.getInt("woman");
				int man = resultSet.getInt("man");
				int total = resultSet.getInt("total");
				
				System.out.println("데이터 값 : " + region +"," + woman +"," + man +"," +total);
				DDto dto = new DDto(man, woman);
				dtos.add(dto);
				
				System.out.println(dtos);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}

		return dtos;
	}
	@SuppressWarnings("unchecked")
	public JSONObject parsing() {
		 System.out.println("parsing 매소드 실행");
		ArrayList<DDto> array = list();
		System.out.println("array size " + array.size());
	     JSONObject jObject = new JSONObject();
	    try {
	        for (int i = 0; i < array.size(); i++) {
	            JSONArray sObject = new JSONArray();
//	            sObject.add( array.get(i).getRegion());
	            sObject.add( array.get(i).getMan());
	            sObject.add( array.get(i).getWoman());
//	            sObject.add( array.get(i).getTotal());
	           jObject.put("item"+i,sObject);
	      
	        }
	        System.out.println("jArray DDAO: " + jObject);
	        
	     
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
		return jObject;
	
	}
}