package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.*;

// �� ����
public class BoardModifyAction implements Action {
	 
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		// ActionForward ��ü ����
		ActionForward forward = new ActionForward();
		
		// get������� �Ѿ�� �� ��ȣ
		int num = Integer.parseInt(request.getParameter("BOARD_NUM"));

		// BoardDAO, BoardBean ��ü ����
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		// �� ���� �ʱ�ȭ : false
		boolean result = false;
		
		// ������ �Խñ� = BoardDAO�� �۾��� ���� Ȯ���ϴ� �޼ҵ忡 ( �۹�ȣ, ����� ��� ��ȣ )
		boolean usercheck = boarddao.isBoardWriter(num, request.getParameter("BOARD_PASS"));

		// �Խñ��� ��й�ȣ�� Ʋ���� ��
		if(usercheck==false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('������ ������ �����ϴ�.');");
			out.println("location.href='./BoardList.bo';");
			out.println("</script>");
			out.close();
			return null;
		}
		// Ʋ���� ������
		try{
			// BoardBean�� �� ����
			boarddata.setBOARD_NUM(num);
			boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));

			// BoardDAO�� �Խñ� �����ϴ� �޼ҵ�� BoardBean�� ����� ������ �ѱ�
			result = boarddao.boardModify(boarddata);

			if(result==false){
				System.out.println("�Խ��� ���� ����");
				return null;
			}
			System.out.println("�Խ��� ���� �Ϸ�");

			// ���� �� ������ �̵�
			
			// ���� ��� true : sendRedirect ���
			forward.setRedirect(true);
			// ���� ��� ����
			forward.setPath("./BoardDetailAction.bo?num="+boarddata.getBOARD_NUM());
			
			return forward;

		}catch(Exception ex){
			ex.printStackTrace();	 
		}

		return null;
	}
}