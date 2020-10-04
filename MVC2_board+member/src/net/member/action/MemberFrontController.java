package net.member.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ��Ʈ�ѷ�
public class MemberFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
       
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// �� �������� �ּ�
    	String RequestURI = request.getRequestURI();

    	// ���̳��� �� ������Ʈ �� ������ �ּ�
    	String contaxtPath = request.getContextPath();

    	// �� �������� �ּҿ��� ���̳��� �� ������Ʈ �� ������ �ּҸ� �߶󳻰� ���� ���� command�� ����.
    	String command = RequestURI.substring(contaxtPath.length());
    	

    	// ���� ��� �� ���� ����� ������ �뵵
    	ActionForward forward  = null;
    	Action action = null;

    	if(command.equals("/Main.me")){
    		// ���� ������
    		forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Main.jsp");
    	
    	}else if(command.equals("/MemberLogin.me")){
    		// �α���
    		forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/qna_member_Login.jsp");
    	
    	}else if(command.equals("/MemberJoin.me")) {
    		// ȸ�� ����
    		forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/qna_member_Join.jsp");
    	
    	}else 	if(command.equals("/MemberLoginAction.me")) {
    		// �α��� üũ
    			action = new MemberLoginAction();
			 try{
				 forward = action.execute(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
    	
    	}else if(command.equals("/MemberJoinAction.me")) {
    		// ȸ�� ���� üũ
    			action = new MemberJoinAction();
			 try{
				 forward = action.execute(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
    	}else if(command.equals("/MemberIntoAction.me")) {
    		// ȸ�� �� ����
    			action = new MemberIntoAction();
			 try{
				 forward = action.execute(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
    	}else if(command.equals("/MemberListAction.me")) {
    		// ȸ�� ���
    			action = new MemberListAction();
			 try{
				 forward = action.execute(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
    	}else if(command.equals("/MemberDeleteAction.me")) {
    		// ȸ�� ����
				action = new MemberDeleteAction();
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
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
