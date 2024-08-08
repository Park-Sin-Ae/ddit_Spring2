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
	<h2>REMOVE</h2>
	<hr/>
	<form action="/item3/remove" method="post" enctype="multipart/form-data">
		<input type="hidden" name="itemId" value="${item.itemId }">
		<table border="1">
			<tr>
				<td>상품명</td>
				<td>
					<input type="text" name="itemName" id="itemName" value="${item.itemName }" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<div class="uploadedList"></div>
				</td>
			</tr>
			<tr>
				<td>개요</td>
				<td>
					<textarea rows="10" cols="30" name="description" disabled="disabled">${item.description }</textarea>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit">Remove</button>
			<button type="button" onclick="javascript:location.href='/item3/list'">List</button>
		</div>
	</form>
</body>
<script type="text/javascript">
$(function() {
	var itemId = ${item.itemId};
	console.log("itemId : " + itemId);
	
	$.getJSON("/item3/getAttach/" + itemId, function(list) {
		$(list).each(function() {
			console.log("data : " + this);
			var data = this;
			var str = "";
			
			if(checkImageType(data)){		// 이미지면 이미지 태그를 이용한 출력
				str = "<div>";
				str += "	<a href='/item3/displayFile?fileName="+data+"'>";
				str += "		<img src='/item3/displayFile?fileName="+getThumbnailName(data)+"'/>";
				str += "	</a>";
				str += "</div>";
			} else {	// 파일이면 파일명에 대한 링크로만 출력
				str = "<div>";
				str += "	<a href='/item3/displayFile?fileName="+data+"'>";
				str += getOriginalName(data);
				str += "	</a>";
				str += "</div>";
			}
			$(".uploadedList").append(str);
		});
	});
	
	item.submit(function(event) {
		event.preventDefault();
		
		var that = $(this);		// form,
		var str = "";
		$(".uploadedList a").each(function(index) {
			var value = $(this).attr("href");
			value = value.substr(28);		// ?fileName= 다음에 나오는 값
					
			str += "<input type='hidden' name='files["+index+"]' value='"+value+"'/>";
		});
		console.log("str = " + str);
		that.append(str);
		that.get(0).submit();
	});
	
	// 임시 파일로 썸네일 이미지 만들기
	function getThumbnailName(fileName) {
		var front = fileName.substr(0, 12);	// /2024/05/29/ 폴더
		var end = fileName.substr(12);		// 뒤 파일명
		console.log(front + " :::: " + end);
		return front + "s_" + end;
	}
	
	// 파일명 추출
	function getOriginalName(fileName) {
		if(checkImageType(fileName)){
			return;
		}
		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);
	}
	
	// 이미지 파일인지 확인
	function checkImageType(fileName) {
		var pattern = /jpg|gif|png|jpeg/i;
		return fileName.match(pattern);	// 패턴과 일치하면 true(너 이미지구나?)
	}
});
</script>
</html>