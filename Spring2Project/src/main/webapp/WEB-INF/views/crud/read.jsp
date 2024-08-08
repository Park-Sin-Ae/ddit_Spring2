<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>READ</h2>
	<hr/>
	<table border="1">
		<tr>
			<td>제목</td>
			<td>${board.title }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.content }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>
				<fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd hh:mm"/>
			</td>
		</tr>
	</table>
	<form action="/crud/board/remove" method="post" id="delForm">
		<input type="hidden" name="boardNo" value="${board.boardNo }"/>
	</form>
	<button type="button" id="modifyBtn">수정</button>
	<button type="button" id="deleteBtn">삭제</button>
	<button type="button" id="listBtn">목록</button>
</body>
<script type="text/javascript">
$(function() {
	var modifyBtn = $("#modifyBtn");
	var deleteBtn = $("#deleteBtn");
	var listBtn = $("#listBtn");
	var delForm = $("#delForm");
	
	modifyBtn.on("click", function() {
		delForm.attr("action", "/crud/board/modify")
		delForm.attr("method", "get")
		delForm.submit();
	});
	
	deleteBtn.on("click", function() {
		if(confirm("정말로 삭제하시겠습니까?")){
			delForm.submit();
		}
	});
	
	listBtn.on("click", function() {
		location.href = "/crud/board/list";
	});
});
</script>
</html>