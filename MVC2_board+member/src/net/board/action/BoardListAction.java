package net.board.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

// 글 목록 보기
public class BoardListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// BoardDAO, ArrayList(List 동적 바인딩) 객체 생성
		BoardDAO boarddao = new BoardDAO();
		List boardlist = new ArrayList();

		// 첫 페이지 번호 초기화 : 1
		int page = 1;
		// 페이지당 글 목록 수 초기화 : 10
		int limit = 10;

		// 페이지값 변화
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// 총 리스트 수를 받아옴.
		int listcount = boarddao.getListCount();
		// DB에서 리스트를 받아옴.
		boardlist = boarddao.getBoardList(page, limit);

		// 총 페이지 수.
		int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95를 더해서 올림 처리.
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
		int endpage = maxpage;

		if (endpage > startpage + 10 - 1) {
			endpage = startpage + 10 - 1;
		}

		request.setAttribute("page", page); // 현재 페이지 수.
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수.
		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수.
		request.setAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지 수.
		request.setAttribute("listcount", listcount); // 글 수.
		request.setAttribute("boardlist", boardlist);

		// ActionForward 객체 생성
		ActionForward forward = new ActionForward();

		// 전송 방식 = false : Forward
		forward.setRedirect(false);
		// 접근 경로 지정
		 forward.setPath("./board/qna_board_list.jsp");

		return forward;
	}
}