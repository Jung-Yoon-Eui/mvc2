package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

// ��� ����
public class BoardReplyView implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ActionForward ��ü ����
		ActionForward forward = new ActionForward();

		// BoardDAO, BoardBean ��ü ����
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		// get������� �Ѿ�� �� ��ȣ
		int num = Integer.parseInt(request.getParameter("num"));

		// BoardBean�� BoardDAO�� �� ���� ���� �޼ҵ忡 �� ��ȣ ����
		boarddata = boarddao.getDetail(num);

		if(boarddata==null){
			System.out.println("���� ������ �̵� ����");
			return null;
		}
		
		System.out.println("���� ������ �̵� �Ϸ�");

		// "boarddata" �̸����� boarddata ��ü ����
		request.setAttribute("boarddata", boarddata);

		// ���� �� ������ �̵�
		
		// ���� ��� = false : Forward  
		forward.setRedirect(false);
		// ���� ��� ����
		forward.setPath("./board/qna_board_reply.jsp");
		
		return forward;
	}
}