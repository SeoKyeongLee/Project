package goldenTime.db;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataInsertSeoul {
	DataSource dataSource;

	public DataInsertSeoul() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		java.sql.Statement stmt = null;
		String filePath = "C:\\Users\\user\\Desktop\\java\\workspace\\goldenTime\\WebContent\\js\\seoul.json";

		String region = "";
		int man = 0;
		int woman = 0;
		int total = 0;

		try {
			String driverClassName = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "goldentime";
			String password = "goldentime";

			// JDBC Driver Loading
			Class.forName(driverClassName);

			// JDBC Connection getting
			conn = DriverManager.getConnection(url, user, password);

			System.out.println("DB 연결 성공");
			System.out.println("** Driver:" + driverClassName + ", Connection:" + conn);

			// 테이블 없을 시 테이블 생성
			String query = "CREATE TABLE seoul(";
		
			query += "region varchar2(50) primary key,";
			query += "man number(3),";
			query += "woman number(3),";
			query += "total number(3))";
			System.out.println("테이블 생성 혹은 이미 존재");
			stmt = conn.createStatement();

			boolean b = stmt.execute(query);

			System.out.println("b : " + b);

		} catch (ClassNotFoundException ex) {
			System.out.println("드라이버 로딩실패");
			ex.printStackTrace();
		} catch (SQLException e) {
			System.out.println("sql 오류 : 이미 생성");

		} finally {
			CloseUtil.close(null, stmt, conn);
		}
		// table create JDBC 로직 종료

		// insert into JDBC 로직
		try {
		    String driverClassName = "oracle.jdbc.driver.OracleDriver";
		    String url = "jdbc:oracle:thin:@localhost:1521:xe";
		    String user = "goldentime";
		    String password = "time";

		    // JDBC Driver Loading
		    Class.forName(driverClassName);
		    // JDBC Connection getting
		    conn = DriverManager.getConnection(url, user, password);

		     System.out.println("DB 연결 성공 ");
		    System.out.println("**Driver : " + driverClassName + ",Connection : " + conn);

		    // JSON 읽어와서 쿼리에 담기위한 사전작업
		    try (Reader reader = new FileReader(filePath)) {
		        JSONParser parser = new JSONParser();
		        Object obj = parser.parse(reader);
		        JSONArray jsonArr = (JSONArray) obj;
		        System.out.println("json size :" + jsonArr.size());

		        // SQL문 작성
		        String SQL = "insert into seoul(region,man,woman,total) values(?,?,?,?)";
		        System.out.println("첫번째 sql 담김");

		        // PreparedStatement 객체 생성, 객체 생성시 SQL 문장 저장
		        pstmt = conn.prepareStatement(SQL);
		        System.out.println("첫번째 sql 연결준비됨");

		        // 다건 JSON객체 (JSONArray)
		        if (!jsonArr.isEmpty()) {
		            conn.setAutoCommit(false);
		            for (int i = 0; i < jsonArr.size(); i++) {
		                System.out.println("반복문 " + i + "번째 실행");
		                JSONObject jsonObj = (JSONObject) jsonArr.get(i);
		                System.out.println((String) jsonObj.get("region"));
		                System.out.println((Long) jsonObj.get("man"));
		                System.out.println((Long) jsonObj.get("woman"));
		                System.out.println((Long) jsonObj.get("total"));


		                region = (String) jsonObj.get("region");
		                man = ((Number) jsonObj.get("man")).intValue();  // Use Number to handle different numeric types
		                woman = ((Number) jsonObj.get("woman")).intValue();
		                total = ((Number) jsonObj.get("total")).intValue();
		           
		                pstmt.setString(1, region);
		                pstmt.setInt(2, man);
		                pstmt.setInt(3, woman);
		                pstmt.setInt(4, total);
		             // PreparedStatement에서 실제 SQL 문을 추출하여 출력
		 
		                int r = pstmt.executeUpdate();
		      
		                System.out.println("SQL 실행:" + r + "개 의 row삽입");
		                conn.commit();
		            }
		        }
		        conn.commit(); // 트랜잭션 커밋
		    } catch (IOException | ParseException e) {
		        e.printStackTrace();
		    }
		} catch (ClassNotFoundException ex) {
		    System.out.println("드라이버 로딩 실패");
		    ex.printStackTrace();
		} catch (SQLException e) {
		    if (e.getSQLState().equals("42Y55")) {
		        System.out.println("테이블 이미 존재");
		    } else {
		        System.out.println("SQL 오류: " + e.getMessage());
		    }
		} finally {
		    CloseUtil.close(null, pstmt, conn);
		}
	}
}
