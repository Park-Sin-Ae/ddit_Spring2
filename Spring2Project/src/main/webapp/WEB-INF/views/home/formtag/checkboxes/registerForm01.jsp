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
	<p>1) 모델에 Map 타입의 데이터를 생성하여 추가한 후에 화면에 전달한다.</p>
	<!-- 
		Map으로 넘긴 데이터를 items 속성에 넣어, Map의 key는 value가 되고 Map의 value는 label의 몸체 값으로 셋팅된다.
	 -->
	<form:form action="/formtag/register" method="post" modelAttribute="member">
		<table border="1">
			<tr>
				<td>취미(hobbyList)</td>
				<td>
					<form:checkboxes items="${hobbyMap }" path="hobbyList"/>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>