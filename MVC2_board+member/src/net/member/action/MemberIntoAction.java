package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// ȸ�� �� ����
public class MemberIntoAction  implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean ��ü ����
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberVO = new MemberBean();
		
		// get������� �Ѿ�� ���� ��ȣ
		int num = Integer.parseInt(request.getParameter("user_no"));
		
		memberVO = memberDAO.getInto(num);

		if(memberVO==null){
			return null;
		}

		// "memberVO" �̸����� memberVO ��ü ����
		request.setAttribute("memberVO", memberVO);
		
		// ���� ��� = false : Forward  
		forward.setRedirect(false);
		// ���� ��� ����
		forward.setPath("./member/qna_member_Into.jsp");
				
		
		return forward;
	}
}
