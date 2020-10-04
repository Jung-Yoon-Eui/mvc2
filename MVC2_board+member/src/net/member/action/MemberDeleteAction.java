package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// 회원 삭제
public class MemberDeleteAction implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();

		try {

			boolean result = false;

			// get방식으로 넘어온 유저 번호
			int num = Integer.parseInt(request.getParameter("user_no"));

			result = memberDAO.setDelete(num);

			if(result==false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원 가입에 실패하였습니다. 다시 시도해 주시기 바랍니다.');");
				out.println("location.href='./MemberListAction.me';");
				out.println("</script>");
				out.close();
				return null;
			}

			// 전송 방식 true : sendRedirect 방식
			forward.setRedirect(true);
			// 접근 경로 지정
			forward.setPath("./MemberListAction.me");

			return forward;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
