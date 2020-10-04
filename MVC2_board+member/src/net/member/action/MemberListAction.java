package net.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// ȸ�� ��� ����
public class MemberListAction  implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean ��ü ����
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberVO = new MemberBean();
		
		List list = new ArrayList();
		list = memberDAO.getList();
		
		 //"list" �̸����� list ��ü ����
		request.setAttribute("list", list);
		
		// ���� ��� = false : Forward 
		forward.setRedirect(false);
		// ���� ��� ����
		forward.setPath("./member/qna_member_List.jsp");
		
		return forward;
	}
}
