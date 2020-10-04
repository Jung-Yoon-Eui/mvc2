package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

// ȸ�� ����
public class MemberJoinAction implements Action{

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		// ActionForward, BoardDAO, BoardBean ��ü ����
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberVO = new MemberBean();

		boolean result = false;
		
		try {
			
		String birthday = request.getParameter("year")+"�� "
						 +request.getParameter("month")+"�� "
						 +request.getParameter("day")+"��"; 
		
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
		
		// ȸ�� ���� ����
		if(result==false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ȸ�� ���Կ� �����Ͽ����ϴ�. �ٽ� �õ��� �ֽñ� �ٶ��ϴ�.');");
			out.println("location.href='./MemberJoin.me';");
			out.println("</script>");
			out.close();
			return null;
		}

		// ���� ��� true : sendRedirect ���
		forward.setRedirect(true);
		// ���� ��� ����
		forward.setPath("./MemberLogin.me");

		return forward;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
