package goldenTime.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.EmrDao;
import goldenTime.dto.CongestionDto;

public class EmrCongestionCommand implements EmrCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		EmrDao dao = new EmrDao();
		ArrayList<CongestionDto> dtos = dao.congestionList();
		request.setAttribute("congestionList", dtos);

	}

}
