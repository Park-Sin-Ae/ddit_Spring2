<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>REGISTER</h2>
	<hr/>
	<form action="/item2/register" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>상품명</td>
				<td>
					<input type="text" name="itemName" id="itemName"/>
				</td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<input type="file" name="pictures" id="picture1"/>
				</td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<input type="file" name="pictures" id="picture2"/>
				</td>
			</tr>
			<tr>
				<td>개요</td>
				<td>
					<textarea rows="10" cols="30" name="description"></textarea>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit">Register</button>
			<button type="button" onclick="javascript:location.href='/item2/list'">List</button>
		</div>
	</form>
</body>
</html>