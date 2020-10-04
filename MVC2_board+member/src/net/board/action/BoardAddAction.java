package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

// �� ���.
public class BoardAddAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{

		// BoardDAO, BoardBean, ActionForward ��ü ����.
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		ActionForward forward = new ActionForward();

		// ������ ���.
		String realFolder = "";
		// ������ ���� �̸�.
		String saveFolder = "boardupload";
		// ���� ������ ����.
		int fileSize = 5*1024*1024;

		// ������ ��� = ������ ����Ǵ� ���� ����� saveFolder.
		realFolder = request.getRealPath(saveFolder);

		// �� ��� : �ʱ�ȭ false.
		boolean result = false;

		try{
			// ���� ���ε� ��ü : �ʱ�ȭ null.
			MultipartRequest multi = null;

			// ���� ���ε� ��ü ���� (  ���� ��� , ������ ���, ���� ������, ���ڵ�, ���ϸ� �ٲٴ� ��Ģ�� �ִ� Ŭ���� ��ü ���� ).
			multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			// BoardBean ��ü ��� : DB�� ���� ���� Ŭ������ �� �ѱ��.
			boarddata.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
			boarddata.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
			boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
			boarddata.setBOARD_FILE(multi.getFilesystemName((String)multi.getFileNames().nextElement()));

			// �� ��� = BoardDAO�� boardInsert�޼ҵ忡 BoardBean�� ���� ������ ����.
			result = boarddao.boardInsert(boarddata);

			if(result==false){ // �� ��� ����
				System.out.println("�Խ��� ��� ����");
				return null;
			}
			
			System.out.println("�Խ��� ��� �Ϸ�");

			// ���� �� ������ �̵�
			
			// ���� ��� true : sendRedirect ���
			forward.setRedirect(true);
			// ���� ��� ����
			forward.setPath("./BoardList.bo");
			
			return forward;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}  	
}