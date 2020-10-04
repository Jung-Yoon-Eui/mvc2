package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// 회원 가입
public class MemberJoinAction implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean 객체 생성
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberVO = new MemberBean();

		boolean result = false;
		
		try {
			
		String birthday = request.getParameter("year")+"년 "
						 +request.getParameter("month")+"월 "
						 +request.getParameter("day")+"일"; 
		
		String chkd = "";
		
		for(String i : request.getParameterValues("chk")) {
			chkd += i+" ";
		}
		
		String address = request.getParameter("postcode")+" "+
						 request.getParameter("address")+" "+
						 request.getParameter("detailAddress")+" "+
						 request.getParameter("extraAddress");
		
		
		memberVO.setIdid(request.getParameter("idid"));
		memberVO.setPw(request.getParameter("pw"));
		memberVO.setMail(request.getParameter("mail"));
		memberVO.setName1(request.getParameter("name1"));
		memberVO.setBirthday(birthday);
		memberVO.setChk(chkd);
		memberVO.setAddress(address);
		
		result = memberDAO.memberJoin(memberVO);
		
		// 회원 가입 실패
		if(result==false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 가입에 실패하였습니다. 다시 시도해 주시기 바랍니다.');");
			out.println("location.href='./MemberJoin.me';");
			out.println("</script>");
			out.close();
			return null;
		}

		// 전송 방식 true : sendRedirect 방식
		forward.setRedirect(true);
		// 접근 경로 지정
		forward.setPath("./MemberLogin.me");

		return forward;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
