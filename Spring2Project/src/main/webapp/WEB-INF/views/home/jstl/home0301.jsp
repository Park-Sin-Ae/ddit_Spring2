<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>7장. JSP</h3>
	<p>3) c:remove</p>
	<c:set value="${member.userId }" var="memberId"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId }</td>
		</tr>
	</table>
	
	<c:set value="hongkildong" target="${member }" property="userId"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${member.userId }</td>
		</tr>
	</table>
	
	<c:remove var="memberId"/>
	
	<p>memberId 변수를 삭제 처리함</p>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId }</td>
		</tr>
	</table>
</body>
</html>