package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

// 게시글 상세 보기
public class BoardDetailAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 

		// 인코딩
		request.setCharacterEncoding("UTF-8");

		// BoardDAO, BoardBean 객체 생성
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		// get방식으로 넘어온 글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		
		// BoardDAO의 조회수 업데이트 메소드에 글 번호 전달
		boarddao.setReadCountUpdate(num);
		
		// BoardBean에 BoardDAO의 글 내용 보기 메소드에 글 번호 전달
		boarddata = boarddao.getDetail(num);

		// 글 내용 보기 메소드에 글 번호를 전달하지 못할 때 
		if(boarddata==null){
			System.out.println("상세보기 실패");
			return null;
		}
		// 글 내용 보기 메소드에 글 번호를 전달하지 할 때
		System.out.println("상세보기 성공");

		// "boarddata" 이름으로 boarddata 객체 전달
		request.setAttribute("boarddata", boarddata);

		// ActionForward 객체 생성
		ActionForward forward = new ActionForward();
		
		// 실행 후 페이지 이동

		// 전송 방식 = false : Forward  
		forward.setRedirect(false);
		// 접근 경로 지정
		forward.setPath("./board/qna_board_view.jsp");
		
		return forward;

	}
}