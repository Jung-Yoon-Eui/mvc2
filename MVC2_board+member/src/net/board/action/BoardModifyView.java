package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

// 게시글 확인
public class BoardModifyView implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ActionForward 객체 생성
		ActionForward forward = new ActionForward();
		// 인코딩
		request.setCharacterEncoding("UTF-8");

		// BoardDAO, BoardBean 객체 생성
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		// get방식으로 넘어온 글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		// BoardBean에 BoardDAO의 글 내용 보기 메소드에 글 번호 전달
		boarddata = boarddao.getDetail(num);

		if(boarddata==null){
			System.out.println("(수정)상세보기 실패");
			return null;
		}

		System.out.println("(수정)상세보기 성공");

		// "boarddata" 이름으로 boarddata 객체 전달
		request.setAttribute("boarddata", boarddata);
		
		// 전송 방식 = false : Forward  
		forward.setRedirect(false);
		// 접근 경로 지정
		forward.setPath("./board/qna_board_modify.jsp");
		
		return forward;
	}
}