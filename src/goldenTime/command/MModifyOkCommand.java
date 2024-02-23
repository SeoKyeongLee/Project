package goldenTime.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goldenTime.dao.MemberDao;
import goldenTime.dto.MemberDto;

public class MModifyOkCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setPw(pw);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setAddress(address);
		
		MemberDao dao = MemberDao.getInstance();
		int ri = dao.updateMember(dto);
		
		try {
			if(ri == 1){
				request.setAttribute("modifyMessage", "회원정보가 성공적으로 업데이트 되었습니다.");
			} else {
				request.setAttribute("modifyMessage", "회원정보 업데이트에 실패했습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/modifyOk.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

}
