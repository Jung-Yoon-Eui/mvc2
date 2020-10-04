package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// 로그인
public class MemberLoginAction  implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
//		MemberBean memberVO = new MemberBean();
		
		try {
			
		String idid = request.getParameter("idid");
		String pw = request.getParameter("pw");
		
		boolean loginChk = memberDAO.memberLogin(idid, pw);
		
		// 로그인 실패
		if(loginChk==false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호가 잘못되었습니다.');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("idid", idid);
		session.setAttribute("pw", pw);

		// 전송 방식 true : sendRedirect 방식
		forward.setRedirect(true);
		// 접근 경로 지정
		forward.setPath("./BoardList.bo");
					
		return forward;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
