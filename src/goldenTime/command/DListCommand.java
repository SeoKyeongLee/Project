package goldenTime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import goldenTime.dao.DDao;

public class DListCommand implements DCommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("DListCommand 실행");

        try {
            DDao dao = new DDao();
            JSONObject obj = dao.parsing();  // Assuming dao.parsing() returns a JSONObject
            System.out.println("obj (command): " + obj);
			String data = obj.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(data);
			System.out.println("data : " + data);
//			request.setAttribute("doctorFetch", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}