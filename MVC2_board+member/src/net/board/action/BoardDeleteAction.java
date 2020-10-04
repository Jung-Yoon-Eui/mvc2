package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.*;

// �Խñ� ����
public class BoardDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ActionForward ��ü ����
		ActionForward forward = new ActionForward();
		// ���ڵ�
		request.setCharacterEncoding("UTF-8");

		// �� ����, ������ �Խñ� ���� �ʱ�ȭ : false
		boolean result = false;
		boolean usercheck = false;

		// get������� �Ѿ�� �� ��ȣ
		int num = Integer.parseInt(request.getParameter("num"));

		// BoardDAO ��ü ����
		BoardDAO boarddao = new BoardDAO();

		// ������ �Խñ� = BoardDAO�� �۾��� ���� Ȯ���ϴ� �޼ҵ忡 ( �۹�ȣ, ����� ��� ��ȣ )
		usercheck = boarddao.isBoardWriter(num, request.getParameter("BOARD_PASS"));

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

		// ������ �Ϸ��� (��й�ȣ�� Ʋ���� ������) =  BoardDAO�� �Խñ� �����ϴ� �޼ҵ�� �Խñ� ��ȣ�� �ѱ�
		result = boarddao.boardDelete(num);

		if(result==false){
			System.out.println("�Խ��� ���� ����");
			return null;
		}

		System.out.println("�Խ��� ���� ����");

		
		// ���� �� ������ �̵�
		
		// ���� ��� true : sendRidirect ���
		forward.setRedirect(true);
		// ���� ��� ����
		forward.setPath("./BoardList.bo");
		
		return forward;
	}
}