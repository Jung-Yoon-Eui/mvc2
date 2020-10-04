package net.board.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

// �� ��� ����
public class BoardListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// BoardDAO, ArrayList(List ���� ���ε�) ��ü ����
		BoardDAO boarddao = new BoardDAO();
		List boardlist = new ArrayList();

		// ù ������ ��ȣ �ʱ�ȭ : 1
		int page = 1;
		// �������� �� ��� �� �ʱ�ȭ : 10
		int limit = 10;

		// �������� ��ȭ
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// �� ����Ʈ ���� �޾ƿ�.
		int listcount = boarddao.getListCount();
		// DB���� ����Ʈ�� �޾ƿ�.
		boardlist = boarddao.getBoardList(page, limit);

		// �� ������ ��.
		int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95�� ���ؼ� �ø� ó��.
		// ���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// ���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
		int endpage = maxpage;

		if (endpage > startpage + 10 - 1) {
			endpage = startpage + 10 - 1;
		}

		request.setAttribute("page", page); // ���� ������ ��.
		request.setAttribute("maxpage", maxpage); // �ִ� ������ ��.
		request.setAttribute("startpage", startpage); // ���� �������� ǥ���� ù ������ ��.
		request.setAttribute("endpage", endpage); // ���� �������� ǥ���� �� ������ ��.
		request.setAttribute("listcount", listcount); // �� ��.
		request.setAttribute("boardlist", boardlist);

		// ActionForward ��ü ����
		ActionForward forward = new ActionForward();

		// ���� ��� = false : Forward
		forward.setRedirect(false);
		// ���� ��� ����
		 forward.setPath("./board/qna_board_list.jsp");

		return forward;
	}
}