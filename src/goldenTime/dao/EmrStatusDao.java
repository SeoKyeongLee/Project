package goldenTime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import goldenTime.dto.EmrInfoDto;
import goldenTime.dto.RealTimeEmrDto;




public class EmrStatusDao {

	DataSource dataSource;
	
	public EmrStatusDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<EmrInfoDto> emrInfo(String area) {
	    ArrayList<EmrInfoDto> dtos = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = dataSource.getConnection();

	        String query = "select dutyName, dutyAddr, dutyTel3 from emr_list where emrArea = ?";

	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, area);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            String dutyName = resultSet.getString("dutyName");
	            String dutyAddr = resultSet.getString("dutyAddr");
	            String dutyTel3 = resultSet.getString("dutyTel3");

	            EmrInfoDto dto = new EmrInfoDto(dutyName, dutyAddr, dutyTel3);
	            dtos.add(dto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    return dtos;
	}
	
	public List<RealTimeEmrDto> realTimeList(){
	    ArrayList<RealTimeEmrDto> dtos = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	    	connection =dataSource.getConnection();
	    	
	    	String query = "select * from realtime_emr ";
	        preparedStatement = connection.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();
	        
	        while(resultSet.next()) {

	        	String name = resultSet.getString("name");
	        	int hvgc = resultSet.getInt("hvgc");
	        	int hvoc = resultSet.getInt("hvoc");
	        	int hperyn = resultSet.getInt("hperyn");
	        	int hvec = resultSet.getInt("hvec");
	        	String emreceive3 = resultSet.getString("emreceive3");
	        	RealTimeEmrDto dto = new RealTimeEmrDto(name,hvgc,hvoc,hperyn,hvec,emreceive3);
	        	dtos.add(dto);
	        }
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
		return dtos;
	}
}
	




