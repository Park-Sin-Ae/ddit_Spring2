<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Spring Form</h3>
	<form:form action="/formtag/hidden/result" method="post" modelAttribute="member">
		<form:hidden path="userId"/>
		<table border="1">
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName"/>
					<font color="crimson">
						<form:errors path="userName"/>
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>