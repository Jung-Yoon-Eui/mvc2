package net.board.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 컨트롤러
public class BoardFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 // 웹 페이지의 주소
		 // ex) http://localhost:8081/MVC2/BoardList.bo
		 String RequestURI = request.getRequestURI();

		 // 다이나믹 웹 프로젝트 명 까지의 주소
		 // ex) http://localhost:8081/MVC2 << 까지
		 String contaxtPath = request.getContextPath();

		 // 웹 페이지의 주소에서 다이나믹 웹 프로젝트 명 까지의 주소를 잘라내고 남은 것을 command에 저장.
		 // ex) [ http://localhost:8081/MVC2 ] < 삭제 하고 command에   BoardList.bo 저장.
		 String command = RequestURI.substring(contaxtPath.length());
		 

		 // 접근 경로 및 전송 방식을 전달할 용도
		 ActionForward forward  = null;
		 Action action = null;

		 
		 // BoardWrite.bo는 DB연동이 필요하지 않은 페이지
		 if(command.equals("/BoardWrite.bo")){
			 
			 forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("./board/qna_board_write.jsp");
			 
		// BoardDelete.bo는 DB연동이 필요하지 않은 페이지 
		 }else if(command.equals("/BoardDelete.bo")){
			 
			 forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("./board/qna_board_delete.jsp");

		
		// 아래는 모두 DB연동이 필요한 페이지
			 
		 }else if(command.equals("/BoardList.bo")){	// 메인 페이지!
			 
			 	action = new BoardListAction();
			 
			 try{

				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }	 
			 
		 }else if(command.equals("/BoardReplyAction.bo")){
			 
			 	// BoardReplyView는 interface Action을 상속 받는 클래스
			 	// Action 클래스로 필요한 클래스를 동적 바인딩.
			 	action = new BoardReplyView();
			 
			 try{
				 			// execute 메소드에 매개변수를 넣으면 ActionForward 타입 > Action 클래스 참고.
				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 
		 }else if(command.equals("/BoardModify.bo")){
			 
			 	action = new BoardModifyView();
			 
			 try{

				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 
		 }else if(command.equals("/BoardAddAction.bo")){
			 
			 	action = new BoardAddAction();
			 
			 try{

				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 
		 }else if(command.equals("/BoardReplyView.bo")){
			 
			 	action = new BoardReplyAction();
			 
			 try{

				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 
		 }else if(command.equals("/BoardModifyAction.bo")){
			 
			 	action = new BoardModifyAction();
			 
			 try{

				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 
		 }else if(command.equals("/BoardDeleteAction.bo")){
			 	
			 	action = new BoardDeleteAction();
			 
			 try{

				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 
		 }else if(command.equals("/BoardDetailAction.bo")){
			 
			 action = new BoardDetailAction();

			 try{

				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 
	if(forward.isRedirect()){
			 // isRedirect = false : Forward
			 response.sendRedirect(forward.getPath());
	}else{
			 // isRedirect = true : sendRedirect
			 RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			 dispatcher.forward(request, response);
		 }
	}
	 
	 
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doProcess(request,response);
	 }  	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doProcess(request,response);
	 }   	  	    
 }