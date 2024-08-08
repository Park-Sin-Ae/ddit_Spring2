<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN' crossorigin='anonymous'>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js'></script>
</head>
<body>
	<!-- 
		Test03Repository에서 구현한 학생 정보를 활용해 로그인을 진행해주세요.
		
			1) 입력한 아이디, 비밀번호와 일치하는 정보가 있다면 info.jsp로 이동해주세요.
				> 이때 일회성 메세지로 "OO님! 환영합니다!" 메세지를 출력해주세요.
			2) 아이디/비밀번호찾기 버튼을 클릭 시, findInfo.jsp로 이동해주세요.
				> 아이디찾기, 비밀번호 찾기를 진행해주세요.
	 -->
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">
					로그인을 진행해주세요.
				</div>
				<div class="card-body">
					<form action="/test03/loginChk.do" method="post" name="loginForm">
						<div class="input-group mb-3">
							<input class="form-control" type="text" name="memId" id="memId" placeholder="아이디를 입력해주세요."/>
						</div>
						<div class="input-group mb-3">
							<input class="form-control" type="text" name="memPw" id="memPw" placeholder="비밀번호를 입력해주세요."/>
						</div>
					</form>
				</div>
				<div class="card-footer">
					<button type="button" class="btn btn-primary mb-2" id="loginBtn">로그인</button>
					<button type="button" class="btn btn-warning mb-2" id="findIdPw">아이디/비밀번호 찾기</button>
				</div>
			</div>
		</div>
		<div class="col-md-4"></div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	var findIdPw = $("#findIdPw");
	var loginBtn = $("#loginBtn");
	
	findIdPw.on("click", function() {
		location.href = "/test03/findInfo.do";
	});
});
var msg = "${msg}";
if(msg == "N") {
	alert("일치하는 정보가 없습니다.");
}

$("#loginBtn").on("click", function() {
	loginForm.submit();
})
</script>
</html>