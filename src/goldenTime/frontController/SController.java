package goldenTime.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.command.SMonthSearchCommand;
import goldenTime.command.SRegionSearchCommand;

/**
 * Servlet implementation class CFrontController
 */
@WebServlet("/congestion/*")
public class SController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("====SController doHandle 호출====");
		
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		String month = request.getParameter("month");
		
		if(com.equals("/congestion/monthSearch")) {
			SMonthSearchCommand monthSearch = new SMonthSearchCommand();
			monthSearch.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/congestion.jsp");
			System.out.println("congestion.jsp한테 forward한다");
			dispatcher.forward(request, response);
		} else if(com.equals("/congestion/region")) {
			SRegionSearchCommand regionSearch = new SRegionSearchCommand();
			regionSearch.execute(request, response);
		}
		
	}

}
