<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page</title>
</head>
<body>
	<form method="post">
		<%
			if (session.getAttribute("idid") == null && session.getAttribute("pw") == null) {
		%>
			<input type="button" value="로그인" onclick="location.href='./MemberLogin.me'"> 
			<input type="button" value="회원 가입" onclick="location.href='./MemberJoin.me'">
		<%
			} else {
		%>
				<%=session.getAttribute("idid")%>님이 로그인 중입니다.<br>
				
		<%		
				if("ADMIN".equals(session.getAttribute("idid")) &&  "ADMIN1234".equals(session.getAttribute("pw"))){
		%>
					<a href='./MemberListAction.me'>관리자 페이지</a>
		<%
				}
		%>
					<a href="./BoardList.bo" >게시판</a>
					<hr>
		<%
			}
		%>
	</form>
</body>
</html>