package goldenTime.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.EmrDao;
import goldenTime.dto.ActualDto;

public class EmrActualListCommand implements EmrCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
			EmrDao dao = new EmrDao();
			ArrayList<ActualDto> dtos = dao.actualLiat();
			request.setAttribute("actualList", dtos);
	}

}
