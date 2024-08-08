<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<form:form action="/crud/board/register" method="post" id="frm" modelAttribute="board">
		<c:if test="${status eq 'u' }">
			<input type="hidden" name="boardNo" value="${board.boardNo }"/>
		</c:if>
		<table border="1">
			<tr>
				<td>제목</td>
				<td>
					<form:input path="title2"/>
					<input type="text" id="title" name="title" value="${board.title }"/>
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" id="writer" name="writer" value="${board.writer }"/>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="30" name="content" id="content">${board.content }</textarea>
				</td>
			</tr>
		</table>
		<div>
			<c:set value="등록" var="btnText"/>
			<c:if test="${status eq 'u' }">
				<c:set value="수정" var="btnText"/>
			</c:if>
			<input type="button" id="addBtn" value="${btnText }"/>
			<c:if test="${status eq 'u' }">
				<input type="button" id="cancelBtn" value="취소"/>
			</c:if>
			<c:if test="${status ne 'u' }">
				<input type="button" id="listBtn" value="목록"/>
			</c:if>
		</div>
	</form:form>
</body>
<script type="text/javascript">
$(function() {
	var frm = $("#frm");
	var addBtn = $("#addBtn");
	var listBtn = $("#listBtn");
	var cancelBtn = $("#cancelBtn");
	
	// 등록 버튼 이벤트
	addBtn.on("click", function() {
		var title = $("#title").val();
		var writer = $("#writer").val();
		var content = $("#content").val();
		
		if(title == null || title == ""){
			alert("제목을 입력해주세요!");
			$("#title").focus();
			return false;
		}
		
		if(writer == null || writer == ""){
			alert("작성자를 입력해주세요!");
			$("#writer").focus();
			return false;
		}
		
		if(content == null || content == ""){
			alert("내용을 입력해주세요!");
			$("#content").focus();
			return false;
		}
		
		if($(this).val() == "수정"){
			frm.attr("action", "/crud/board/modify");
		}
		
		frm.submit();
	});
	
	// 목록 버튼 이벤트
	listBtn .on("click", function() {
		location.href = "/crud/board/list";
	});
	
	cancelBtn .on("click", function() {
		location.href = "/crud/board/read?boardNo=${board.boardNo}";
	});
});
</script>
</html>