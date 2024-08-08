<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>LIST</h2>
	<hr/>
	<a href="/item2/register">등록</a>
	<table border="1">
		<tr>
			<td align="center" width="80">상품 ID</td>
			<td align="center" width="320">상품명</td>
			<td align="center" width="80">편집</td>
			<td align="center" width="80">삭제</td>
		</tr>
		<c:forEach items="${itemList }" var="item">
			<tr>
				<td>${item.itemId }</td>
				<td>${item.itemName }</td>
				<td>
					<a href="/item2/modify?itemId=${item.itemId }">상품편집</a>
				</td>
				<td>
					<a href="/item2/remove?itemId=${item.itemId }">상품제거</a>
				</td>
			</tr>		
		</c:forEach>
	</table>
</body>
</html>