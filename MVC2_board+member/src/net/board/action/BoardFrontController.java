package net.board.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ��Ʈ�ѷ�
public class BoardFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 // �� �������� �ּ�
		 // ex) http://localhost:8081/MVC2/BoardList.bo
		 String RequestURI = request.getRequestURI();

		 // ���̳��� �� ������Ʈ �� ������ �ּ�
		 // ex) http://localhost:8081/MVC2 << ����
		 String contaxtPath = request.getContextPath();

		 // �� �������� �ּҿ��� ���̳��� �� ������Ʈ �� ������ �ּҸ� �߶󳻰� ���� ���� command�� ����.
		 // ex) [ http://localhost:8081/MVC2 ] < ���� �ϰ� command��   BoardList.bo ����.
		 String command = RequestURI.substring(contaxtPath.length());
		 

		 // ���� ��� �� ���� ����� ������ �뵵
		 ActionForward forward  = null;
		 Action action = null;

		 
		 // BoardWrite.bo�� DB������ �ʿ����� ���� ������
		 if(command.equals("/BoardWrite.bo")){
			 
			 forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("./board/qna_board_write.jsp");
			 
		// BoardDelete.bo�� DB������ �ʿ����� ���� ������ 
		 }else if(command.equals("/BoardDelete.bo")){
			 
			 forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("./board/qna_board_delete.jsp");

		
		// �Ʒ��� ��� DB������ �ʿ��� ������
			 
		 }else if(command.equals("/BoardList.bo")){	// ���� ������!
			 
			 	action = new BoardListAction();
			 
			 try{

				 forward = action.execute(request, response);

			 }catch(Exception e){
				 e.printStackTrace();
			 }	 
			 
		 }else if(command.equals("/BoardReplyAction.bo")){
			 
			 	// BoardReplyView�� interface Action�� ��� �޴� Ŭ����
			 	// Action Ŭ������ �ʿ��� Ŭ������ ���� ���ε�.
			 	action = new BoardReplyView();
			 
			 try{
				 			// execute �޼ҵ忡 �Ű������� ������ ActionForward Ÿ�� > Action Ŭ���� ����.
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