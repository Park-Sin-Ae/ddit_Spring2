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
	<p>dateValueShort : ${dateValueShort }</p>
	<fmt:parseDate value="${dateValueShort }" type="date" dateStyle="short" var="dateShort"/>
	<p>dateShort : ${dateShort }</p>
	<br/>
	<p>dateValueMedium : ${dateValueMedium }</p>
	<fmt:parseDate value="${dateValueMedium }" type="date" dateStyle="medium" var="dateMedium"/>
	<p>dateShort : ${dateMedium }</p>
	<br/>
	<p>dateValueLong : ${dateValueLong }</p>
	<fmt:parseDate value="${dateValueLong }" type="date" dateStyle="long" var="dateLong"/>
	<p>dateShort : ${dateLong }</p>
	<br/>
	<p>dateValueFull : ${dateValueFull }</p>
	<fmt:parseDate value="${dateValueFull }" type="date" dateStyle="full" var="dateFull"/>
	<p>dateShort : ${dateFull }</p>
</body>
</html>