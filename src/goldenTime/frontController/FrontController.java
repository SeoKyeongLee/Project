package goldenTime.frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.command.EmrCommand;

/**
 * Servlet implementation class CFrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
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
		System.out.println("====FrontCtroller doHandle 호출====");
		
		request.setCharacterEncoding("utf-8");
		
		String viewPage = null;
	    EmrCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		String month = request.getParameter("month");
		
		//혼잡도 서비스 
		if(com.equals("/monthSearch.do")) { 
	    	viewPage = "/congestion/monthSearch";
		} else if(com.equals("/region.do")) {
	    	viewPage = "/congestion/region";
		} 
		//회원관리 서비스
		else if(com.equals("/loginOk.do")) {
		    viewPage = "/member/loginOk";
		} else if(com.equals("/logout.do")) {
		    viewPage = "/member/logout";
		} else if(com.equals("/join.do")) {
		    viewPage = "/member/join";
		} else if(com.equals("/joinOk.do")) {
		    viewPage = "/member/joinOk";
		} else if(com.equals("/modify.do")) {
		    viewPage = "/member/modify";
		} else if(com.equals("/modifyOk.do")) {
		    viewPage = "/member/modifyOk";
		} 
		//게시판 서비스
		else if(com.equals("/list.do")) {
		    viewPage = "/board/list";
		} else if(com.equals("/content_view.do")) {
		    viewPage = "/board/content_view";
		} else if(com.equals("/write_view.do")) {
		    viewPage = "/board/write_view";
		} else if(com.equals("/write.do")) {
		    viewPage = "/board/write";
		} else if(com.equals("/reply_view.do")) {
		    viewPage = "/board/reply_view";
		} else if(com.equals("/reply.do")) {
		    viewPage = "/board/reply";
		} else if(com.equals("/delete.do")) {
		    viewPage = "/board/delete";
		} else if(com.equals("/boardModify.do")) {
		    viewPage = "/board/modify";
		} 
		
		//응급실 정보, 현황 서비스
		else if (com.equals("/emrData.do")) {
	    	viewPage = "/emrdcon/emrData";
	    } else if(com.equals("/emrInfo.do")) {
			viewPage = "/emstcon/emrInfo";
		}else if(com.equals("/realTimeEmr.do")) {
			viewPage = "/emstcon/realTimeEmr";
		}else if (com.equals("/emrData.do")) {
	    	viewPage = "/emrdcon/emrData";
	    } else if(com.equals("/emrInfo.do")) {
			viewPage = "/emstcon/emrInfo";
		}else if(com.equals("/realTimeEmr.do")) {
			viewPage = "/emstcon/realTimeEmr";
		}else if(com.equals("/distanceToER.do")) {
			viewPage = "/emstcon/distanceToER";
		}

		//인적자원 재배치 서비스
		else if(com.equals("/data.do")) {
			viewPage = "/doctor/data";
		} else if(com.equals("/doctor.do")) {
			viewPage = "/doctor/doctor";
		}
		
		
	      RequestDispatcher dispatcher=request.getRequestDispatcher(viewPage);
	      dispatcher.forward(request, response);
	}

}