package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

// 글 등록.
public class BoardAddAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// BoardDAO, BoardBean, ActionForward 객체 생성.
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		ActionForward forward = new ActionForward();

		// 저장할 경로.
		String realFolder = "";
		// 저장할 폴더 이름.
		String saveFolder = "boardupload";
		// 파일 사이즈 지정.
		int fileSize = 5*1024*1024;

		// 저장할 경로 = 파일이 저장되는 실제 경로의 saveFolder.
		realFolder = request.getRealPath(saveFolder);

		// 글 등록 : 초기화 false.
		boolean result = false;

		try{
			// 파일 업로드 객체 : 초기화 null.
			MultipartRequest multi = null;

			// 파일 업로드 객체 생성 (  접근 경로 , 저장할 경로, 파일 사이즈, 인코딩, 파일명 바꾸는 규칙이 있는 클래스 객체 생성 ).
			multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			// BoardBean 객체 사용 : DB을 위한 빈즈 클래스에 값 넘기기.
			boarddata.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
			boarddata.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
			boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
			boarddata.setBOARD_FILE(multi.getFilesystemName((String)multi.getFileNames().nextElement()));

			// 글 등록 = BoardDAO의 boardInsert메소드에 BoardBean에 넣은 데이터 전송.
			result = boarddao.boardInsert(boarddata);

			if(result==false){ // 글 등록 실패
				System.out.println("게시판 등록 실패");
				return null;
			}
			
			System.out.println("게시판 등록 완료");

			// 실행 후 페이지 이동
			
			// 전송 방식 true : sendRedirect 방식
			forward.setRedirect(true);
			// 접근 경로 지정
			forward.setPath("./BoardList.bo");
			
			return forward;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}  	
}