<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>REGISTER</h2>
	<hr/>
	<form action="/crud/member/register" method="post" id="member">
		<table border="1">
			<tr>
				<td>userId</td>
				<td>
					<input type="text" id="userId" name="userId"/>
				</td>
			</tr>
			<tr>
				<td>userPw</td>
				<td>
					<input type="text" id="userPw" name="userPw"/>
				</td>
			</tr>
			<tr>
				<td>userName</td>
				<td>
					<input type="text" id="userName" name="userName"/>
				</td>
			</tr>
		</table>
		<input type="button" id="registerBtn" value="Register"/>
		<input type="button" id="listBtn" value="List"/>
	</form>
</body>
<script type="text/javascript">
$(function() {
	var member = $("#member");
	var registerBtn = $("#registerBtn");
	var listBtn = $("#listBtn");
	
	registerBtn.on("click", function() {
		var userId = $("#userId").val();
		var userPw = $("#userPw").val();
		var userName = $("#userName").val();
		
		if(userId == null || userId == "") {
			alert("아이디를 입력해주세요!");
			return false;
		}
		
		if(userPw == null || userPw == "") {
			alert("비밀번호를 입력해주세요!");
			return false;
		}
		
		if(userName == null || userName == "") {
			alert("이름을 입력해주세요!");
			return false;
		}
		member.submit();		// 서버로 데이터 전송
	});
	
	listBtn.on("click", function() {
		location.href = "/crud/member/list";
	});
});
</script>
</html>