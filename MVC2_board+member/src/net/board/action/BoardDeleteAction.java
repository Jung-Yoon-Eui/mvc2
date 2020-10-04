package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.*;

// 게시글 삭제
public class BoardDeleteAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// ActionForward 객체 생성
		ActionForward forward = new ActionForward();
		// 인코딩
		request.setCharacterEncoding("UTF-8");

		// 글 삭제, 삭제할 게시글 선택 초기화 : false
		boolean result = false;
		boolean usercheck = false;

		// get방식으로 넘어온 글 번호
		int num = Integer.parseInt(request.getParameter("num"));

		// BoardDAO 객체 생성
		BoardDAO boarddao = new BoardDAO();

		// 삭제할 게시글 = BoardDAO의 글쓴이 인지 확인하는 메소드에 ( 글번호, 등록한 비밀 번호 )
		usercheck = boarddao.isBoardWriter(num, request.getParameter("BOARD_PASS"));

		// 게시글의 비밀번호가 틀렸을 때
		if(usercheck==false){

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("location.href='./BoardList.bo';");
			out.println("</script>");
			out.close();
			return null;
		}

		// 삭제를 하려면 (비밀번호가 틀리지 않으면) =  BoardDAO의 게시글 삭제하는 메소드로 게시글 번호를 넘김
		result = boarddao.boardDelete(num);

		if(result==false){
			System.out.println("게시판 삭제 실패");
			return null;
		}

		System.out.println("게시판 삭제 성공");

		
		// 실행 후 페이지 이동
		
		// 전송 방식 true : sendRidirect 방식
		forward.setRedirect(true);
		// 접근 경로 지정
		forward.setPath("./BoardList.bo");
		
		return forward;
	}
}