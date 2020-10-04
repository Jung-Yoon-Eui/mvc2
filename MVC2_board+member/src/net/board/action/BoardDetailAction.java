package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

// �Խñ� �� ����
public class BoardDetailAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 

		// ���ڵ�
		request.setCharacterEncoding("UTF-8");

		// BoardDAO, BoardBean ��ü ����
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		// get������� �Ѿ�� �� ��ȣ
		int num = Integer.parseInt(request.getParameter("num"));
		
		// BoardDAO�� ��ȸ�� ������Ʈ �޼ҵ忡 �� ��ȣ ����
		boarddao.setReadCountUpdate(num);
		
		// BoardBean�� BoardDAO�� �� ���� ���� �޼ҵ忡 �� ��ȣ ����
		boarddata = boarddao.getDetail(num);

		// �� ���� ���� �޼ҵ忡 �� ��ȣ�� �������� ���� �� 
		if(boarddata==null){
			System.out.println("�󼼺��� ����");
			return null;
		}
		// �� ���� ���� �޼ҵ忡 �� ��ȣ�� �������� �� ��
		System.out.println("�󼼺��� ����");

		// "boarddata" �̸����� boarddata ��ü ����
		request.setAttribute("boarddata", boarddata);

		// ActionForward ��ü ����
		ActionForward forward = new ActionForward();
		
		// ���� �� ������ �̵�

		// ���� ��� = false : Forward  
		forward.setRedirect(false);
		// ���� ��� ����
		forward.setPath("./board/qna_board_view.jsp");
		
		return forward;

	}
}