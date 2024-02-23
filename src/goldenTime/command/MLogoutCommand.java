package goldenTime.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MLogoutCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
	        response.sendRedirect("login.jsp");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
