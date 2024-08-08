<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Spring Form</h3>
	<form:form action="" method="post" modelAttribute="member">
		<table border="1">
			<tr>
				<td>
					<form:label path="userId">유저ID</form:label>
				</td>
				<td>
					<form:input path="userId"/>
					<font color="crimson">
						<form:errors path="userId"/>
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>