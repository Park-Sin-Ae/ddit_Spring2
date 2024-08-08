<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>HTTP 메소드 매핑 테스트 페이지</h1>
	<hr/>
	
	<form action="/board/http/register">
		<input type="submit" value="register(GET)"/>
	</form>
	<form action="/board/http/register" method="POST">
		<input type="submit" value="register(POST)"/>
	</form>
	<form action="/board/http/modify">
		<input type="submit" value="modify(GET)"/>
	</form>
	<form action="/board/http/modify" method="POST">
		<input type="submit" value="modify(POST)"/>
	</form>
	<form action="/board/http/remove" method="POST">
		<input type="submit" value="remove(POST)"/>
	</form>
	<form action="/board/http/list">
		<input type="submit" value="list(GET)"/>
	</form>
	
</body>
</html>