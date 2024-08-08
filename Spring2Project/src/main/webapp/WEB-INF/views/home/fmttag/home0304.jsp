<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>now : ${now }</p>
	
	<!-- 
		y : 년
		M : 월
		d : 일
		H : 시간
		m : 분
		s : 초
		z : 나라 표기 시
		a : 오전/오후
	 -->
	<p>pattern : <fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
	<p>pattern : <fmt:formatDate value="${now }" pattern="a h:mm"/> </p>
	<p>pattern : <fmt:formatDate value="${now }" pattern="z a h:mm"/> </p>
</body>
</html>