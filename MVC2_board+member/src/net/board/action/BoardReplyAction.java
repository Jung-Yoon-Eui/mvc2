package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

// ��� �ޱ�
public class BoardReplyAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ���ڵ�
		request.setCharacterEncoding("UTF-8");
		// ActionForward ��ü ����
		ActionForward forward = new ActionForward();

		// BoardDAO, BoardBean ��ü ����
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		
		// ���  ?? �� ��  int Ÿ��??
		int result = 0;

		// BoardBean�� �� ����
		boarddata.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
		boarddata.setBOARD_NAME(request.getParameter("BOARD_NAME"));
		boarddata.setBOARD_PASS(request.getParameter("BOARD_PASS"));
		boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		boarddata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
		boarddata.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
		boarddata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));

		// ��� = BoardDAO�� �� �亯 �޼ҵ�� BoardBean�� ����� ���� �ѱ�
		result = boarddao.boardReply(boarddata);

		if(result==0){
			System.out.println("���� ����");
			return null;
		}

		System.out.println("���� �Ϸ�");

		
		// ���� �� ������ �̵�
		
		// ���� ��� true : sendRedirect ���
		forward.setRedirect(true);
		// ���� ��� ����
		forward.setPath("./BoardDetailAction.bo?num="+result);
		
		return forward;
	}  	
}