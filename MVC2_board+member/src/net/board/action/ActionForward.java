package net.board.action;

// 접근 경로 및 전송 방식을 담을 클래스
public class ActionForward {
	
	// 전송 방식 ( true :  sendRedirect , false : Forward )
	private boolean isRedirect = false;
	
	// 접근 경로
	private String path = null;
	
	
	public boolean isRedirect(){
		return isRedirect;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setRedirect(boolean b){
		isRedirect=b;
	}
	
	public void setPath(String string){
		path=string;
	}
}