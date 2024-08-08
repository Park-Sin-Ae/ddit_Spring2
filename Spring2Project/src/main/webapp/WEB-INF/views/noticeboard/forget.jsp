<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="">
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<p class="h4">
				<b>아이디찾기</b>
			</p>
		</div>
		<div class="card-body">
			<p class="login-box-msg">아이디 찾기는 이메일, 이름을 입력하여 찾을 수 있습니다.</p>
			<form action="" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="이메일을 입력해주세요." id="email">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="이름을 입력해주세요." id="name">
				</div>
				<div class="input-group mb-3">
					<p> 
						회원님의 아이디는 <span id="findId">[000]</span> 입니다.
					</p>
				</div>
				<div class="row">
					<div class="col-12">
						<button type="button" class="btn btn-primary btn-block" id="findIdBtn">아이디찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br />
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<p href="" class="h4">
				<b>비밀번호찾기</b>
			</p>
		</div>
		<div class="card-body">
			<p class="login-box-msg">비밀번호 찾기는 아이디, 이메일, 이름을 입력하여 찾을 수 있습니다.</p>
			<form action="" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="아이디를 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="이메일을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="이름을 입력해주세요.">
				</div>
				<div class="input-group mb-3">
					<p>
						회원님의 비밀번호는 [0000] 입니다.
					</p>
				</div>
				<div class="row">
					<div class="col-12">
						<button type="button" class="btn btn-primary btn-block" id="findPwBtn">비밀번호찾기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<br/>
	<div class="card card-outline card-secondary">
		<div class="card-header text-center">
			<h4>MAIN MENU</h4>
			<button type="button" class="btn btn-secondary btn-block" onclick="javascript:location.href='/notice/login.do'">로그인</button>
		</div>
	</div>
</div>
<script>
$(function() {
	var findIdBtn = $("#findIdBtn");
	var findPwBtn = $("#findPwBtn");
	var findId = $("#findId");
	
	findIdBtn.on("click", function() {
		console.log("아이디찾기 버튼 클릭...!");
		
		var email = $("#email").val();
		var name = $("#name").val();
		
		if(email == null || email == ""){
			alert("이메일을 입력해주세요!");
		}
		
		if(name == null || name == ""){
			alert("이름을 입력해주세요!");
		}
		
		// 키 : NoticeMemberVO 값 : 태그 안에 있는 값(id, class)
		var data = {
				memEmail : email,
				memName : name
		};
		
		$.ajax({
			url : "/notice/idForget.do",
			type : "post",
			data : JSON.stringify(data),
			dataType : "text",		// produce라는 속성과 일치시킨다.
			// dataType을 사용하지 않아도 되며 , controller 에 return type이 String 이기 때문에
			// String으로 받아온다.
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				if(res === "empty"){
					res = "존재하지 않는 회원입니다!"
				}
				$("#idResult").html(res).css
				findId.text(data);
			}
			
		})
	});
});
</script>