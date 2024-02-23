package goldenTime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.BoardDao;
import goldenTime.dto.BoardDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("contentCommand execute() 호출");
		String bId = request.getParameter("bId");
		BoardDao dao = BoardDao.getInstance();
		BoardDto dto = dao.contentView(bId);

		// 사용자가 로그인 상태인지 확인
		boolean isLoggedIn = checkIfLoggedIn(request);
		
		// 사용자가 로그인한 경우, 로그인한 사용자가 글쓴이인지 확인
		if (isLoggedIn) {
			boolean isAuthor = dao.isAuthor(bId, getLoggedInUser(request));		
			request.setAttribute("isAuthor", isAuthor);
		}
		request.setAttribute("content_view", dto);
	}
	
	private boolean checkIfLoggedIn(HttpServletRequest request) {
		return "yes".equals(request.getSession().getAttribute("ValidMem"));
	}

	private String getLoggedInUser(HttpServletRequest request) {
		return (String)request.getSession().getAttribute("name");
	}

}
