package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// �α���
public class MemberLoginAction  implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean ��ü ����
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
//		MemberBean memberVO = new MemberBean();
		
		try {
			
		String idid = request.getParameter("idid");
		String pw = request.getParameter("pw");
		
		boolean loginChk = memberDAO.memberLogin(idid, pw);
		
		// �α��� ����
		if(loginChk==false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���̵� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("idid", idid);
		session.setAttribute("pw", pw);

		// ���� ��� true : sendRedirect ���
		forward.setRedirect(true);
		// ���� ��� ����
		forward.setPath("./BoardList.bo");
					
		return forward;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
