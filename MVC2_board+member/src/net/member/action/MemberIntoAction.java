package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// 회원 상세 정보
public class MemberIntoAction  implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberVO = new MemberBean();
		
		// get방식으로 넘어온 유저 번호
		int num = Integer.parseInt(request.getParameter("user_no"));
		
		memberVO = memberDAO.getInto(num);

		if(memberVO==null){
			return null;
		}

		// "memberVO" 이름으로 memberVO 객체 전달
		request.setAttribute("memberVO", memberVO);
		
		// 전송 방식 = false : Forward  
		forward.setRedirect(false);
		// 접근 경로 지정
		forward.setPath("./member/qna_member_Into.jsp");
				
		
		return forward;
	}
}
