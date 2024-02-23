package goldenTime.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goldenTime.dao.MemberDao;
import goldenTime.dto.MemberDto;

public class MLoginOkCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDao dao = MemberDao.getInstance();
		int checkNum = dao.userCheck(id, pw);
		
		try {
			if (checkNum == -1) {
				// 아이디가 존재하지 않을 때
				request.setAttribute("errorMessage", "아이디가 존재하지 않습니다.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/loginOk.jsp");
				dispatcher.forward(request, response);
			} else if (checkNum == 0) {
				// 비밀번호가 다를 때
				request.setAttribute("errorMessage", "비밀번호를 재확인하세요.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/loginOk.jsp");
				dispatcher.forward(request, response);
			} else if (checkNum == 1) {
				MemberDto dto = dao.getMember(id);
				if (dto == null) {
					// 존재하지 않는 회원일 때
					request.setAttribute("errorMessage", "존재하지 않는 회원입니다.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/loginOk.jsp");
					dispatcher.forward(request, response);
				} else {
					// 로그인 성공 시
					String name = dto.getName();
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("name", name);
					session.setAttribute("ValidMem", "yes");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
