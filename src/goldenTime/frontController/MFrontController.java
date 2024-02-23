package goldenTime.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.command.MCommand;
import goldenTime.command.MJoinOkCommand;
import goldenTime.command.MLoginOkCommand;
import goldenTime.command.MLogoutCommand;
import goldenTime.command.MModifyCommand;
import goldenTime.command.MModifyOkCommand;

/**
 * Servlet implementation class MFrontController
 */
@WebServlet("/member/*")
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 호출");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 호출");
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo 호출");
		request.setCharacterEncoding("utf-8");
		
		String ViewPage = null;
		MCommand command = null;
		
		String uri = request.getRequestURI();
		String conpath = request.getContextPath();
		String com = uri.substring(conpath.length());
		
		if(com.equals("/member/join")) {
			ViewPage = "/join.jsp";
		} else if(com.equals("/member/login")) {
			ViewPage = "/login.jsp";
		} else if(com.equals("/member/logout")) {
			command = new MLogoutCommand();
			command.execute(request, response);
			return;
		} else if(com.equals("/member/modify")) {
			command = new MModifyCommand();
			command.execute(request, response);
			ViewPage = "/modify.jsp";
		} else if(com.equals("/member/loginOk")) {
			command = new MLoginOkCommand();
			command.execute(request, response);
			return;
		} else if(com.equals("/member/joinOk")) {
			command = new MJoinOkCommand();
			command.execute(request, response);
			return;
		} else if(com.equals("/member/modifyOk")) {
			command = new MModifyOkCommand();
			command.execute(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPage);
		dispatcher.forward(request, response);
	}
}
