<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Page</h1>
	<form action="/intercept/login" method="post">
		아이디 : <input type="text" name="userId"/><br/>
		비밀번호 : <input type="text" name="userPw"/><br/>
		<input type="submit" value="login"/>
	</form>
</body>
</html>