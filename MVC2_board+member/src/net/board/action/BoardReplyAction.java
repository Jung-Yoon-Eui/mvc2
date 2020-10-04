package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

// 답글 달기
public class BoardReplyAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward 객체 생성
		ActionForward forward = new ActionForward();

		// BoardDAO, BoardBean 객체 생성
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		
		// 답글  ?? 얜 왜  int 타입??
		int result = 0;

		// BoardBean에 값 저장
		boarddata.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
		boarddata.setBOARD_NAME(request.getParameter("BOARD_NAME"));
		boarddata.setBOARD_PASS(request.getParameter("BOARD_PASS"));
		boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		boarddata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
		boarddata.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
		boarddata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));

		// 답글 = BoardDAO의 글 답변 메소드로 BoardBean에 저장된 값을 넘김
		result = boarddao.boardReply(boarddata);

		if(result==0){
			System.out.println("답장 실패");
			return null;
		}

		System.out.println("답장 완료");

		
		// 실행 후 페이지 이동
		
		// 전송 방식 true : sendRedirect 방식
		forward.setRedirect(true);
		// 접근 경로 지정
		forward.setPath("./BoardDetailAction.bo?num="+result);
		
		return forward;
	}  	
}