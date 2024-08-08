<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>RESULT</h3>
	<table border="1">
		<tr>
			<td>유저ID</td>
			<td>${member.userId }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.userName }</td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td>${member.password }</td>
		</tr>
		<tr>
			<td>E-Mail</td>
			<td>${member.email }</td>
		</tr>
		<tr>
			<td>소개</td>
			<td>${member.introduction }</td>
		</tr>
		<tr>
			<td>취미</td>
			<td>
				<c:forEach items="${member.hobbyList }" var="hobby">
					<c:out value="${hobby }"/><br/>
				</c:forEach>
			</td>
		</tr>
	</table>
</body>
</html>