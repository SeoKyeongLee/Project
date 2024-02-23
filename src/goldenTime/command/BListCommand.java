package goldenTime.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import goldenTime.dao.BoardDao;
import goldenTime.dto.BoardDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = BoardDao.getInstance();
		
		//페이지네이션
		int currentPage = 1; //기본 페이지
		String pageParam = request.getParameter("page");
		
		//페이지 매개변수가 null이 아니고 비어있지 않을 시 정수로 파싱하고 현재 페이지로 설정
		if(pageParam != null && !pageParam.isEmpty()) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		int pageSize = 7; // 페이지당 표시할 레코드 수
		
		// 가져올 레코드의 시작 인덱스 계산
		int startIndex = (currentPage - 1) * pageSize;
		
		//시작 인덱스와 페이지 크기를 지정하여 BoardDto 객체 목록 가져오기
		ArrayList<BoardDto> dtos = dao.list(startIndex, pageSize);
		request.setAttribute("list", dtos);
		
		int totalRecords = dao.getTotalRecords();
		//모든 레코드를 표시하는 데 필요한 전체 페이지 수 계산
	    int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

	    
	    PaginatedData paginatedData = new PaginatedData(currentPage, totalPages);

	    
	    request.setAttribute("paginatedData", paginatedData);
	}
	
	
}
