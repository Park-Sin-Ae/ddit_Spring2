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
	<p>1) escapeXml, default 속성 이용한 c:out</p>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${member.userId }</td>
		</tr>
		<tr>
			<td>member.userId</td>
			<td><c:out value="${member.userId }"/></td>
		</tr>
		<!-- 
			escapeXml 속성의 기본값은 true 이고, 특수문자를 변환하여 출력
			(태그가 포함되어 있다면 태그가 함께 출력)
			escapeXml 속성을 false로 설정하면 태그를 인식하여 출력
			태그가 태그의 역할을 하도록 출력된다. (escapeXml 속성의 값이 false일 때) 
		 -->
		<tr>
			<td>member.userId</td>
			<td><c:out value="${member.userId }" escapeXml="false"/></td>
		</tr>
		<!-- 
			default 속성을 설정하면, 넘어온 값이 null인 경우에 default에 설정되어 있는 값으로 대체된다.
		 -->
		<tr>
			<td>member.password</td>
			<td><c:out value="${member.password }" default="12341234"/></td>
		</tr>
	</table>
</body>
</html>