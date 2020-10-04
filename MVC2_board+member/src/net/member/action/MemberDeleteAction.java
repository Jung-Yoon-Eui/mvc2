package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// ȸ�� ����
public class MemberDeleteAction implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean ��ü ����
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();

		try {

			boolean result = false;

			// get������� �Ѿ�� ���� ��ȣ
			int num = Integer.parseInt(request.getParameter("user_no"));

			result = memberDAO.setDelete(num);

			if(result==false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('ȸ�� ���Կ� �����Ͽ����ϴ�. �ٽ� �õ��� �ֽñ� �ٶ��ϴ�.');");
				out.println("location.href='./MemberListAction.me';");
				out.println("</script>");
				out.close();
				return null;
			}

			// ���� ��� true : sendRedirect ���
			forward.setRedirect(true);
			// ���� ��� ����
			forward.setPath("./MemberListAction.me");

			return forward;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
