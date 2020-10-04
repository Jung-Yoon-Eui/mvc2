package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.*;

// 글 수정
public class BoardModifyAction implements Action {
	 
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		// ActionForward 객체 생성
		ActionForward forward = new ActionForward();
		
		// get방식으로 넘어온 글 번호
		int num = Integer.parseInt(request.getParameter("BOARD_NUM"));

		// BoardDAO, BoardBean 객체 생성
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		// 글 수정 초기화 : false
		boolean result = false;
		
		// 수정할 게시글 = BoardDAO의 글쓴이 인지 확인하는 메소드에 ( 글번호, 등록한 비밀 번호 )
		boolean usercheck = boarddao.isBoardWriter(num, request.getParameter("BOARD_PASS"));

		// 게시글의 비밀번호가 틀렸을 때
		if(usercheck==false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href='./BoardList.bo';");
			out.println("</script>");
			out.close();
			return null;
		}
		// 틀리지 않으면
		try{
			// BoardBean에 값 저장
			boarddata.setBOARD_NUM(num);
			boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));

			// BoardDAO의 게시글 수정하는 메소드로 BoardBean에 저장된 값들을 넘김
			result = boarddao.boardModify(boarddata);

			if(result==false){
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");

			// 실행 후 페이지 이동
			
			// 전송 방식 true : sendRedirect 방식
			forward.setRedirect(true);
			// 접근 경로 지정
			forward.setPath("./BoardDetailAction.bo?num="+boarddata.getBOARD_NUM());
			
			return forward;

		}catch(Exception ex){
			ex.printStackTrace();	 
		}

		return null;
	}
}