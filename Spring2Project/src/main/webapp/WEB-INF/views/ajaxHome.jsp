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
	<h1>Ajax Home</h1>
	<hr/>
	
	<form>
		boardNo : <input type="text" name="boardNo" id="boardNo"/><br/> 	
		title : <input type="text" name="title" id="title"/><br/>	
		content : <input type="text" name="content" id="content"/><br/> 	
		writer : <input type="text" name="writer" id="writer"/><br/>
		<input type="button" id="btn" value="전송"/> 	
	</form>
	<div>
		<h3>Headers 매핑</h3>
		<button type="button" id="putBtn">MODIFY(PUT)</button>
		<button type="button" id="putHeaderBtn">MODIFY(PUT With Headers)</button>
		
		<h3>Content Type 매핑</h3>
		<button type="button" id="postBtn">MODIFY(POST)</button>
		<button type="button" id="putJsonBtn">MODIFY(PUT JSON)</button>
		<button type="button" id="putXmlBtn">MODIFY(PUT XML)</button>
		
		<h3>Accept 매핑</h3>
		<button type="button" id="getBtn">READ</button>
		<button type="button" id="getJsonBtn">READ(JSON)</button>
		<button type="button" id="getXmlBtn">READ(XML)</button>
	</div>
</body>
<script type="text/javascript">
$(function() {
	var putBtn = $("#putBtn");
	var putHeaderBtn = $("#putHeaderBtn");
	
	var postBtn = $("#postBtn");
	var putJsonBtn = $("#putJsonBtn");
	var putXmlBtn = $("#putXmlBtn");
	
	var getBtn = $("#getBtn");
	var getJsonBtn = $("#getJsonBtn");
	var getXmlBtn = $("#getXmlBtn");
	
	// Headers 매핑 Modift(put) 버튼 이벤트
	putBtn.on("click", function() {
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
		};
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "put", 
			data : JSON.stringify(boardObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log("result : " + res);
				if(res === "SUCCESS") {
					alert(res);
				}
			}
		});
	});
	
	// Headers 매핑 Modify(put with headers) 버튼 이벤트
	putHeaderBtn.on("click", function() {
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
		};
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "put",
			headers : {
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify(boardObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log("res : " + res);
				if(res === "SUCCESS") {
					alert(res);
				}
			}
		});
	});
	
	// Content Type 매핑 postBtn 이벤트
	postBtn.on("click", function() {
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
		};
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "post",
			data : JSON.stringify(boardObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log("res : " + res);
				if(res === "SUCCESS"){
					alert(res);
				}
			}
		});
	});
	
	// Content Type 매핑 putJsonBtn 이벤트
	putJsonBtn.on("click", function() {
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
		};
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "put",
			data : JSON.stringify(boardObject),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				console.log("res : " + res);
				if(res === "SUCCESS"){
					alert(res);
				}
			}
		});
	});
	
	// Content Type 매핑 putXmlBtn 이벤트
	putXmlBtn.on("click", function() {
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var xmlData = "";
		xmlData += "<Board>";
		xmlData += "<boardNo>" + boardNo + "</boardNo>";
		xmlData += "<title>" + title + "</title>";
		xmlData += "<content>" + content + "</content>";
		xmlData += "<writer>" + boardNo + "</writer>";
		xmlData += "</Board>";
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "put",
			contentType : "application/xml; charset=utf-8",
			data : xmlData,
			success : function(res) {
				console.log(res);
				if(res === "SUCCESS") {
					alert(res);
				}
			}
		});
	});
	
	// Accept 매핑 getBtn 클릭 이벤트
	getBtn.on("click", function() {
		var boardNo = $("#boardNo").val();
		
		$.get("/board/" + boardNo, function(data) {
			console.log("data : " + data);
			alert(JSON.stringify(data));
		});
	});
	
	// Accept 매핑 getJsonBtn 클릭 이벤트
	getJsonBtn.on("click", function() {
		var boardNo = $("#boardNo").val();
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "get",
			headers : {
				"Accept" : "application/json"
			},
			success : function(res) {
				console.log(res);
				alert(JSON.stringify(res));
			}
		});
	});
	
	// Accept 매핑 getXmlBtn 클릭 이벤트
	getXmlBtn.on("click", function() {
		var boardNo = $("#boardNo").val();
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "get",
			headers : {
				"Accept" : "application/xml"
			},
			success : function(res) {
				console.log(res);
				alert(xmlToString(res));
			}
		});
	});
	 
});

function xmlToString(xmlData) {
	var xmlString;
	
	// window.ActiveObject는 ActiveObject를 지원하는 브라우저면
	// Object를 리턴하고 그렇지 않으면 null을 리턴합니다.
	if(window.ActiveXObject){
		xmlString = xmlData.xml;
	}else {
		xmlString = (new XMLSerializer()).serializeToString(xmlData);
	}
	
	return xmlString;
}
</script>
</html>