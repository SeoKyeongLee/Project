package goldenTime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goldenTime.dao.BoardDao;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bId = request.getParameter("bId");
//		String bName = request.getParameter("bName");
		String authorName = (String) session.getAttribute("name"); //로그인한 사용자 이름
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		System.out.println("bGroup: " + bGroup);
		System.out.println("bStep: " + bStep);
		System.out.println("bIndent: " + bIndent);

		
		BoardDao dao = BoardDao.getInstance();
		dao.reply(bId, authorName, bTitle, bContent, bGroup, bStep, bIndent);
	}

}
