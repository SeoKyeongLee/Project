package goldenTime.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.command.DCommand;
import goldenTime.command.DListCommand;

/**
 * Servlet implementation class DFrontController
 */

@WebServlet("/doctor/*")


public class DFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}
	private void actionDo (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		DCommand command = null;

		
		String url = request.getRequestURI();
		String conPath =request.getContextPath();
		String com = url.substring(conPath.length());
	
		if(com.equals("/doctor/doctor")) {
			command = new DListCommand();
			command.execute(request, response);
			viewPage = "person.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}else if(com.equals("/doctor/data")) {
			command = new DListCommand();
			command.execute(request, response);
		}
		
		
	}
}
