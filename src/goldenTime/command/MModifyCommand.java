package goldenTime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.MemberDao;
import goldenTime.dto.MemberDto;

public class MModifyCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = dao.getMember(id);
		
		request.setAttribute("modify", dto);
	}

}
