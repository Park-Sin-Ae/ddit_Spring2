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
	<h1>Ajax 방식 요청 처리</h1>
	<h3>Ajax 방식 요청 처리</h3>
	<hr/>
	
	<form>
		userId : <input type="text" name="userId" id="userId" value=""/><br/>		
		password : <input type="text" name="password" id="password" value=""/><br/>		
	</form>
	
	<p>3) 객체 타입의 JSON 요청 데이터 @RequestBody 지정하여 자바빈즈 매개변수로 처리한다.</p>
	<button type="button" id="registerBtn03">registerBtn03</button>
	
	<p>4) 객체 타입의 JSON 요청 데이터는 문자열 매개변수로 처리할 수 없다.</p>
	<button type="button" id="registerBtn04">registerBtn04</button>
	
	<p>5) 요청 URL에 쿼리파라미터를 붙여서 전달하면 문자열 매개변수로 처리한다.</p>
	<button type="button" id="registerBtn05">registerBtn05</button>
	
	<p>
		7) 객체 배열 타입의 JSON 요청 데이터를 자바빈즈 요소를 가진 리스트 컬렉션 매개변수에 @RequestBody
		 어노테이션을 지정하여 처리한다. 
	</p>
	<button type="button" id="registerBtn07">registerBtn07</button>
	
	<p>
		8) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody를 지정하여 중첩된 자바빈즈 매개변수로
		처리한다.
	</p>
	<button type="button" id="registerBtn08">registerBtn08</button>
	
	<p>
		9) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody를 지정하여 중첩된 자바빈즈 매개변수로
		처리한다.(리스트 데이터를 받는다.)
	</p>
	<button type="button" id="registerBtn09">registerBtn09</button>
</body>

<script type="text/javascript">
$(function() {
	var registerBtn03 = $("#registerBtn03");
	var registerBtn04 = $("#registerBtn04");
	var registerBtn05 = $("#registerBtn05");
	var registerBtn07 = $("#registerBtn07");
	var registerBtn08 = $("#registerBtn08");
	var registerBtn09 = $("#registerBtn09");
	
	// 3) 객체 타입의 JSON 요청 데이터 @RequestBody를 지정하여 자바빈즈 매개변수로 처리한다.	
	registerBtn03.on("click", function() {
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		var userObject = {
			userId : userId,
			password : password
		}
		
		$.ajax({
			url : "/ajax/register03",
			type : "post",
			data : JSON.stringify(userObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log(res);
				if(res === "SUCCESS"){
					alert(res);
				}
				
			}
		});
	});
	
	// 4) 객체 타입의 JSON 요청 데이터는 문자열 매개변수로 처리할 수 없다.
	registerBtn04.on("click", function() {
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		var userObject = {
			userId : userId,
			password : password
		}
		
		$.ajax({
			url : "/ajax/register04",
			type : "post",
			data : JSON.stringify(userObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log(res);
				if(res === "SUCCESS"){
					alert(res);
				}
				
			}
		});
	});
	
	// 5) 요청 URL에 쿼리파라미터를 붙여서 전달하면 문자열 매개변수로 처리한다.
	registerBtn05.on("click", function() {
		var userId = $("#userId").val();
		var password = $("#password").val();
		
		var userObject = {
			userId : userId,
			password : password
		}
		
		$.ajax({
			url : "/ajax/register05?userId="+userId,
			type : "post",
			data : JSON.stringify(userObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log(res);
				if(res === "SUCCESS"){
					alert(res);
				}
				
			}
		});
	});
	
	// 7) 객체 배열 타입의 JSON 요청 데이터를 자바빈즈 요소를 가진 리스트 컬렉션 매개변수에 @RequestBody
	// 어노테이션을 지정하여 처리한다.
	registerBtn07.on("click", function() {
		var userId = $("#userId").val();
		var password = $("#password").val();
	
		var userObjectArray = [
			{userId : "name01", password : "pw1"},
			{userId : "name02", password : "pw2"},
		];
		
		$.ajax({
			url : "/ajax/register07",
			type : "post",
			data : JSON.stringify(userObjectArray),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log(res);
				if(res === "SUCCESS"){
					alert(res);
				}
				
			}
		});
	});
	
	// 8) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody를 지정하여 중첩된 자바빈즈 매개변수로
	//	처리한다.
	registerBtn08.on("click", function() {
		var userId = $("#userId").val();
		var password = $("#password").val();
	
		var userObject = { 
			userId : userId,
			password : password,
			address : {
				postCode : "010908",
				location : "Daejeon"
			}
		};
		
		$.ajax({
			url : "/ajax/register08",
			type : "post",
			data : JSON.stringify(userObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log(res);
				if(res === "SUCCESS"){
					alert(res);
				}
				
			}
		});
	});
	
	// 9) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody를 지정하여 중첩된 자바빈즈 매개변수로
	//	처리한다.(리스트 데이터를 받는다.)
	registerBtn09.on("click", function() {
		var userId = $("#userId").val();
		var password = $("#password").val();
	
		var userObject = { 
			userId : userId,
			password : password,
			cardList : [
				{no : "12345", validMonth : "20221018"},
				{no : "67890", validMonth : "20221019"}
			]
		
		};
		
		$.ajax({
			url : "/ajax/register09",
			type : "post",
			data : JSON.stringify(userObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log(res);
				if(res === "SUCCESS"){
					alert(res);
				}
				
			}
		});
	});
	
});
</script>
</html>