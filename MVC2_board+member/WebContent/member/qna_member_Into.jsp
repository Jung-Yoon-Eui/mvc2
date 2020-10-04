<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.member.db.*" %>
<%@ include file="../Top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보</title>
</head>
<body>
<%
	MemberBean mem = (MemberBean)request.getAttribute("memberVO");
%>
	<table border="1" style="border-collapse: collapse">
		<tr>
			<th colspan="2" bgcolor="skyblue"><%=mem.getUser_no() %>번 회원 상세 정보</th>
		</tr>
		<tr>
			<th>아이디</th>
			<td>
				<%=mem.getIdid() %>
			</td>
		</tr>
		<tr>
			<th>비밀 번호</th>
			<td>
				<%=mem.getPw() %>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<%=mem.getMail() %>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<%=mem.getName1() %>
			</td>
		</tr>
		<tr>
			<th colspan="2" bgcolor="skyblue">개인 신상 정보</th>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<%=mem.getAddress() %>
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
				<%=mem.getBirthday() %>
			</td>
		</tr>
		<tr>
			<th>관심 분야</th>
			<td>
				<%=mem.getChk() %>
			</td>
		</tr>
		<tr align="center" valign="middle">
		<td colspan="2">
			<font size=2>
			<a href="./MemberDeleteAction.me?user_no=<%=mem.getUser_no() %>">[삭제]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
</body>
</html>