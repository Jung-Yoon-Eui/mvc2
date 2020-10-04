<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../Top.jsp"%>
<html>
<head>
<title>MVC 게시판</title>
</head>
<body>
<hr>
<%
	int num = Integer.parseInt(request.getParameter("num"));
%>
<form name="deleteForm" action="./BoardDeleteAction.bo?num=<%=num %>" method="post">
<table border=1>
<tr>
	<td>
		<font size=2>글 비밀번호 : </font>
	</td>
	<td>
		<input name="BOARD_PASS" type="password">
	</td>
</tr>
<tr>
	<td colspan=2 align=center>
		<a href="javascript:deleteForm.submit()">삭제</a>
		&nbsp;&nbsp;
		<a href="javascript:history.go(-1)">돌아가기</a>
	</td>
</tr>
</table>
</form>
</body>
</html>