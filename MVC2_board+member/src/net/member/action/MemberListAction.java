package net.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// 회원 목록 보기
public class MemberListAction  implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberVO = new MemberBean();
		
		List list = new ArrayList();
		list = memberDAO.getList();
		
		 //"list" 이름으로 list 객체 전달
		request.setAttribute("list", list);
		
		// 전송 방식 = false : Forward 
		forward.setRedirect(false);
		// 접근 경로 지정
		forward.setPath("./member/qna_member_List.jsp");
		
		return forward;
	}
}
