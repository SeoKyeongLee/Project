package goldenTime.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.EmrDao;
import goldenTime.dto.NeedDto;

public class EmrNeedListCommand implements EmrCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
			EmrDao dao = new EmrDao();
			ArrayList<NeedDto> dtos = dao.needList();
			request.setAttribute("needList", dtos);

	}

}
