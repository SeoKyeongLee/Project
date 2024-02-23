package goldenTime.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.EmrStatusDao;
import goldenTime.dto.EmrInfoDto;




public class EmrInfoCommand implements EmrCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EmrStatusDao dao = new EmrStatusDao();
		String area = request.getParameter("area");
		System.out.println(area);
		List<EmrInfoDto> resultList = null;
            try {
				resultList = dao.emrInfo(area);
				System.out.println("DAO가 데이터를 가지고 왔나?"+ resultList==null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            request.setAttribute("list", resultList);
		}
	}