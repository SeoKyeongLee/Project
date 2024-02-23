package goldenTime.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.EmrStatusDao;
import goldenTime.dto.RealTimeEmrDto;

public class RealTimeCommand implements EmrCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmrStatusDao dao = new EmrStatusDao();
		List<RealTimeEmrDto> resultList = dao.realTimeList();
		request.setAttribute("list", resultList);
		
	}

}
