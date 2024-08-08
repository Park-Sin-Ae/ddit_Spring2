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
	<h2>MODIFY</h2>
	<hr/>
	<form action="/item3/modify" method="post" enctype="multipart/form-data" id="item">
		<input type="hidden" name="itemId" value="${item.itemId }">
		<table border="1">
			<tr>
				<td>상품명</td>
				<td>
					<input type="text" name="itemName" id="itemName" value="${item.itemName }"/>
				</td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<input type="file" id="inputFile"/>
					<div class="uploadedList">
					
					</div>
				</td>
			</tr>
			<tr>
				<td>개요</td>
				<td>
					<textarea rows="10" cols="30" name="description">${item.description }</textarea>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit">Modify</button>
			<button type="button" onclick="javascript:location.href='/item3/list'">List</button>
		</div>
	</form>
</body>
<script type="text/javascript">
$(function() {
	var inputFile = $("#inputFile");		// 파일 선택 Element
	var uploadedList = $(".uploadedList");
	var item = $("#item");
	
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
				str += "	<span>X</span>";
				str += "</div>";
			} else {	// 파일이면 파일명에 대한 링크로만 출력
				str = "<div>";
				str += "	<a href='/item3/displayFile?fileName="+data+"'>";
				str += getOriginalName(data);
				str += "	</a>";
				str += "	<span>X</span>";
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
	
	inputFile.on("change", function(event) {
		console.log("change...!"); 
		
		var files = event.target.files;
		var file = files[0];
		
		var formData = new FormData();
		formData.append("file", file);
		
		$.ajax({
			url : "/item3/uploadAjax",
			type : "post",
			contentType : false,
			data : formData,
			dataType : "text",
			processData : false,
			success : function(data) {
				console.log(data);
				
				var str = "";
				
				if(checkImageType(data)){		// 이미지면 이미지 태그를 이용한 출력
					str = "<div>";
					str += "	<a href='/item3/displayFile?fileName="+data+"'>";
					str += "		<img src='/item3/displayFile?fileName="+getThumbnailName(data)+"'/>";
					str += "	</a>";
					str += "	<span>X</span>";
					str += "</div>";
				} else {	// 파일이면 파일명에 대한 링크로만 출력
					str = "<div>";
					str += "	<a href='/item3/displayFile?fileName="+data+"'>";
					str += getOriginalName(data);
					str += "	</a>";
					str += "	<span>X</span>";
					str += "</div>";
				}
				$(".uploadedList").append(str);
			}
		});
	});
	
	// X 버튼 클릭 시 div 영역 삭제하기
	uploadedList.on("click", "span", function() {
		$(this).parent("div").remove();
	})
	
	// 임시 파일로 썸네일 이미지 만들기
	function getThumbnailName(fileName) {
		var front = fileName.substr(0, 12);	// /2024/05/29/ 폴더
		var end = fileName.substr(12);		// 파일명
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