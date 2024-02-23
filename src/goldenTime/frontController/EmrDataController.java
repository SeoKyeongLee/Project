package goldenTime.frontController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.command.EmrActualListCommand;
import goldenTime.command.EmrCommand;
import goldenTime.command.EmrCongestionCommand;

import goldenTime.command.EmrNeedListCommand;

import goldenTime.command.JsonResponseService;

@WebServlet("/emrdcon/*")
public class EmrDataController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("application/json;charset=utf-8");
	    EmrCommand command = null;

	    String uri = request.getRequestURI();
	    String conPath = request.getContextPath();
	    String com = uri.substring(conPath.length());

	    if (com.equals("/emrdcon/emrData")) {
	        EmrCommand actualCommand = new EmrActualListCommand();
	        EmrCommand needCommand = new EmrNeedListCommand();
	        EmrCommand congestCommand = new EmrCongestionCommand();


	        // 데이터를 먼저 가져오고 설정
	        actualCommand.execute(request, response);
	        needCommand.execute(request, response);
	        congestCommand.execute(request, response);

	        
	        
	        // JSON 객체 생성 및 응답 데이터 전송
	        JsonResponseService jsonResponseCommand = new JsonResponseService();
	        jsonResponseCommand.execute(request, response);
	    } 
	}
}
