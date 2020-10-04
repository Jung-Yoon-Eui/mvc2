package net.member.action;

import javax.servlet.http.*;

public interface Action {
						// execute 메소드로 받아온 값을 ActionForward 타입으로 저장한다.
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	// controller에  DB연결이 필요한 페이지(interface Action 클래스를 상속 받음)를 요청할 때,
	// Action으로 해당 페이지 기능을 수행할 클래스를 동적바인딩하여 객체를 생성하여 접근 경로와 전송 방식을 전달한다.
	
}
