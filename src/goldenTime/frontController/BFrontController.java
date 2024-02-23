package goldenTime.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.command.BCommand;
import goldenTime.command.BContentCommand;
import goldenTime.command.BDeleteCommand;
import goldenTime.command.BListCommand;
import goldenTime.command.BModifyCommand;
import goldenTime.command.BReplyCommand;
import goldenTime.command.BReplyViewCommand;
import goldenTime.command.BWriteCommand;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("/board/*")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BFrontController() {
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
		System.out.println("Board controller actionDo 호출");
		request.setCharacterEncoding("utf-8");
		
		String ViewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conpath = request.getContextPath();
		String com = uri.substring(conpath.length());
		
		if(com.equals("/board/list")) {
			command = new BListCommand();
			command.execute(request, response);
			ViewPage = "/list.jsp";
		} else if(com.equals("/board/write")) {
			command = new BWriteCommand();
			command.execute(request, response);
			ViewPage = "/list.do";
		} else if(com.equals("/board/write_view")) {
			ViewPage = "/write_view.jsp";
		} else if(com.equals("/board/content_view")) {
			command = new BContentCommand();
			command.execute(request, response);
			ViewPage = "/content_view.jsp";
			System.out.println("content_view.jsp에 forward");
		} else if(com.equals("/board/delete")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			ViewPage = "/list.do";
		} else if(com.equals("/board/modify")) {
			command = new BModifyCommand();
			command.execute(request, response);
			ViewPage = "/list.do";
		} else if(com.equals("/board/reply_view")) {
			command = new BReplyViewCommand();
			command.execute(request, response);
			ViewPage = "/reply_view.jsp";
		} else if(com.equals("/board/reply")) {
			command = new BReplyCommand();
			command.execute(request, response);
			ViewPage = "/list.do";
		}
		
		//RequestDispatcher에서 forward 시에는 컨텍스트 루트에서부터의 상대 경로를 사용해야 함
		RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPage);
		dispatcher.forward(request, response);
		
	}

}
