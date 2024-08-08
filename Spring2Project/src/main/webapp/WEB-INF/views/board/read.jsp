<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>READ</h1>
	
	<form action="/board/get" method="get">
		<button type="submit" name="modify">Modify</button>
		<button type="submit" name="remove">Remove</button>
	</form>
	
	<a href="/board/get?list">List</a>
</body>
</html>