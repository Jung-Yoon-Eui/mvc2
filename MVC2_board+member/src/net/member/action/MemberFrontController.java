package net.member.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 컨트롤러
public class MemberFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
       
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// 웹 페이지의 주소
    	String RequestURI = request.getRequestURI();

    	// 다이나믹 웹 프로젝트 명 까지의 주소
    	String contaxtPath = request.getContextPath();

    	// 웹 페이지의 주소에서 다이나믹 웹 프로젝트 명 까지의 주소를 잘라내고 남은 것을 command에 저장.
    	String command = RequestURI.substring(contaxtPath.length());
    	

    	// 접근 경로 및 전송 방식을 전달할 용도
    	ActionForward forward  = null;
    	Action action = null;

    	if(command.equals("/Main.me")){
    		// 메인 페이지
    		forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Main.jsp");
    	
    	}else if(command.equals("/MemberLogin.me")){
    		// 로그인
    		forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/qna_member_Login.jsp");
    	
    	}else if(command.equals("/MemberJoin.me")) {
    		// 회원 가입
    		forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/qna_member_Join.jsp");
    	
    	}else 	if(command.equals("/MemberLoginAction.me")) {
    		// 로그인 체크
    			action = new MemberLoginAction();
			 try{
				 forward = action.execute(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
    	
    	}else if(command.equals("/MemberJoinAction.me")) {
    		// 회원 가입 체크
    			action = new MemberJoinAction();
			 try{
				 forward = action.execute(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
    	}else if(command.equals("/MemberIntoAction.me")) {
    		// 회원 상세 정보
    			action = new MemberIntoAction();
			 try{
				 forward = action.execute(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
    	}else if(command.equals("/MemberListAction.me")) {
    		// 회원 목록
    			action = new MemberListAction();
			 try{
				 forward = action.execute(request, response);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
    	}else if(command.equals("/MemberDeleteAction.me")) {
    		// 회원 삭제
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
