package goldenTime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.BoardDao;
import goldenTime.dto.BoardDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId = request.getParameter("bId");
		System.out.println(bId);
		if (bId != null && !bId.isEmpty()) {
			BoardDao dao = BoardDao.getInstance();
			BoardDto dto = dao.reply_view(bId);
			request.setAttribute("reply_view", dto);
		}
	}

}
