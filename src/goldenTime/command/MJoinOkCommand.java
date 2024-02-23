package goldenTime.command;

import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import goldenTime.dao.MemberDao;
import goldenTime.dto.MemberDto;

public class MJoinOkCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("joinOk command execute() 호출");
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		MemberDto dto = new MemberDto();
		dto.setId(id);
        dto.setPw(pw);
        dto.setName(name);
        dto.setEmail(email);
        dto.setPhone(phone);
        dto.setAddress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		MemberDao dao = MemberDao.getInstance();
		try {
            // 아이디가 이미 존재하는지 확인
            if (dao.confirmId(id) == 1) {
                request.setAttribute("joinMessage", "이미 존재하는 아이디입니다.");
            } else {
                // 새 회원 삽입
                int ri = dao.insertMember(dto);
                if (ri == 1) {
                    session.setAttribute("id", id);
                    request.setAttribute("joinMessage", "회원 가입이 성공적으로 완료되었습니다.");
                } else {
                    request.setAttribute("joinMessage", "회원가입에 실패했습니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	System.out.println("joinOk.jsp로 forward");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/joinOk.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
			
	}

}
