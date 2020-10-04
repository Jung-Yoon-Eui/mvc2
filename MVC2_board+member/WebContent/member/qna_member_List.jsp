<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="net.member.db.*" %>
<%@ include file="../Top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
<table border=1>
			<tr>
				<th>회원 번호</th>
				<th>회원 id</th>
				<th>회원 이름</th>
				<th>회원 정보</th>
				<th>회원 삭제</th>
			</tr>
<%
	List list = (List) request.getAttribute("list");

	for(int i=0; i < list.size(); i++){
	
		MemberBean mem = (MemberBean)list.get(i);
%>
			<tr>
				<td><%=mem.getUser_no() %></td>
				<td><%=mem.getIdid() %></td>
				<td><%=mem.getName1() %></td>
				<td align="center"><a href="./MemberIntoAction.me?user_no=<%=mem.getUser_no() %>">상세 정보</a></td>
				<td align="center"><a href="./MemberDeleteAction.me?user_no=<%=mem.getUser_no() %>">회원 삭제</a></td>
			</tr>
<%
	}
%>
	</table>
</body>
</html>