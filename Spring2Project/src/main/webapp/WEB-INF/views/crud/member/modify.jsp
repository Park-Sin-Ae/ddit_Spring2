<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>MODIFY</h2>
	<hr/>
	<form action="/crud/member/modify" method="post" id="member">
		<input type="hidden" name="userNo" value="${member.userNo }">
		<table border="1">
			<tr>
				<td>userId</td>
				<td>
					<input type="text" name="userId" value="${member.userId }" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td>userName</td>
				<td>
					<input type="text" name="userName" id="userName" value="${member.userName }"/>
				</td>
			</tr>
			<tr>
				<td>auth-1</td>
				<td>
					
					<select name="authList[0].auth">
						<option value="">==선택해주세요==</option>
						<option value="ROLE_USER" <c:if test="${member.authList[0].auth eq 'ROLE_USER' }">selected</c:if>>사용자</option>
						<option value="ROLE_MEMBER"<c:if test="${member.authList[0].auth eq 'ROLE_MEMBER' }">selected</c:if>>회원</option>
						<option value="ROLE_ADMIN"<c:if test="${member.authList[0].auth eq 'ROLE_ADMIN' }">selected</c:if>>관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>auth-2</td>
				<td>
					<select name="authList[1].auth">
						<option value="">==선택해주세요==</option>
						<option value="ROLE_USER" <c:if test="${member.authList[1].auth eq 'ROLE_USER' }">selected</c:if>>사용자</option>
						<option value="ROLE_MEMBER"<c:if test="${member.authList[1].auth eq 'ROLE_MEMBER' }">selected</c:if>>회원</option>
						<option value="ROLE_ADMIN"<c:if test="${member.authList[1].auth eq 'ROLE_ADMIN' }">selected</c:if>>관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>auth-3</td>
				<td>
					<select name="authList[2].auth">
						<option value="">==선택해주세요==</option>
						<option value="ROLE_USER" <c:if test="${member.authList[2].auth eq 'ROLE_USER' }">selected</c:if>>사용자</option>
						<option value="ROLE_MEMBER"<c:if test="${member.authList[2].auth eq 'ROLE_MEMBER' }">selected</c:if>>회원</option>
						<option value="ROLE_ADMIN"<c:if test="${member.authList[2].auth eq 'ROLE_ADMIN' }">selected</c:if>>관리자</option>
					</select>
				</td>
			</tr>
		</table>
		<button type="button" id="modifyBtn">Modify</button>
		<button type="button" id="cancelBtn">Cancel</button>
	</form>
</body>
<script type="text/javascript">
$(function() {
	var modifyBtn = $("#modifyBtn");
	var cancelBtn = $("#cancelBtn");
	var member = $("#member");
	
	modifyBtn.on("click", function() {
		var userName = $("#userName").val();
		
		if(userName == null || userName == ""){
			alert("이름을 입력해주세요!");
			return false;
		}
		member.submit();
	});
	
	cancelBtn.on("click", function() {
		location.href = "/crud/member/read?userNo=${member.userNo}";
	});
});

</script>
</html>