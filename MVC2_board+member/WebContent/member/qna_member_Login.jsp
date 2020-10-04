<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Top.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<h2>로그인 하세요.</h2>
<form action="./MemberLoginAction.me" method="post">
	[ID] : <input type="text" name="idid"><br>
	[PW] : <input type="password" name="pw"><br>
	
	<input type="submit" value="로그인">
	<input type="button" value="회원 가입"  onclick="location.href='./MemberJoin.me'">
</form>
</body>
</html>
