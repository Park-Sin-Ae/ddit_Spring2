<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="register-box">
<div class="card card-outline card-danger mt-4 mb-4">
	<div class="card-header text-center">
		<a href="" class="h1"><b>DDIT</b>BOARD</a>
	</div>
	<div class="card-body">
		<p class="login-box-msg">회원가입</p>

		<form action="/notice/signup.do" method="post" id="signupForm"
			enctype="multipart/form-data">
			<div class="input-group mb-3 text-center">
				<img class="profile-user-img img-fluid img-circle" id="profileImg"
					src="${pageContext.request.contextPath }/resources/dist/img/AdminLTELogo.png" alt="User profile picture"
					style="width: 150px;">
			</div>
			<div class="input-group mb-3">
				<label for="inputDescription">프로필 이미지</label>
			</div>
			<div class="input-group mb-3">
				<div class="custom-file">
					<input type="file" class="custom-file-input" name="imgFile"
						id="imgFile"> <label class="custom-file-label"
						for="imgFile">프로필 이미지를 선택해주세요</label>
				</div>
			</div>
			<div class="input-group mb-3">
				<label for="inputDescription">프로필 정보</label>
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="memId" name="memId"
					placeholder="아이디를 입력해주세요"> 
					<span class="input-group-append">
					<button type="button" class="btn btn-secondary btn-flat"
						id="idCheckBtn">중복확인</button>
				</span> 
				<span class="error invalid-feedback" style="display: block;">${errors.memId }</span>
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="memPw" name="memPw"
					placeholder="비밀번호를 입력해주세요">
					 <span class="error invalid-feedback" style="display: block;">${errors.memPw }</span>
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="memName" name="memName" value="${member.memName }" placeholder="이름을 입력해주세요"> 
					<span class="error invalid-feedback" style="display: block;">${errors.memName }</span>
			</div>
			<div class="input-group mb-3">
				<div class="form-group clearfix">
					<div class="icheck-primary d-inline">
						<input type="radio" id="memGenderM" name="memGender" value="M"
							checked="checked"> <label for="memGenderM">남자&nbsp;</label>
					</div>
					<div class="icheck-primary d-inline">
						<input type="radio" id="memGenderF" name="memGender" value="F">
						<label for="memGenderF">여자 </label>
					</div>
				</div>
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="memEmail" name="memEmail" value="${member.memEmail }" placeholder="이메일을 입력해주세요">
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="memPhone" name="memPhone" value="${member.memPhone }" placeholder="전화번호를 입력해주세요">
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="memPostCode" name="memPostcode" value="${member.memPostcode }">
					<span class="input-group-append">
						<button type="button" class="btn btn-secondary btn-flat" onclick="DaumPostcode()">우편번호 찾기</button>
				</span>
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="memAddress1" value="${member.memAddress1 }"	name="memAddress1" placeholder="주소를 입력해주세요">
			</div>
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="memAddress2" value="${member.memAddress2 }"	name="memAddress2" placeholder="상세주소를 입력해주세요">
			</div>
			<div class="input-group mb-3">
				<div id="map" style="width:100%; height:300px; display:none;"></div>
			</div>
			<div class="row">
				<div class="col-8">
					<div class="icheck-primary">
						<input type="checkbox" id="memAgree" name="memAgree" value="Y">
						<label for="memAgree">개인정보처리방침</label>
					</div>
				</div>
				<div class="col-4">
					<button type="button" class="btn btn-dark btn-block" id="signupBtn">가입하기</button>
				</div>
				<button type="button" class="btn btn-secondary btn-block mt-4"
					onclick="javascript:location.href='/notice/login.do'">뒤로가기</button>
			</div>
			<sec:csrfInput/>
		</form>
	</div>
</div>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f01060a32f56265b19ff206a374877fa&libraries=services"></script>
<script>
$(function(){
   var imgFile = $("#imgFile"); 		// 프로필 이미지 선택 Element
   var profileImg = $("#profileImg");   // 프로필 이미지 태그
   var idCheckBtn = $("#idCheckBtn");   // 중복확인 버튼 Element
   var memAddress2 = $("#memAddress2"); // 상세주소 포커스 아웃 이벤트 줄 Element(주소에 맞는 지도 띄우기)
   var signupBtn = $("#signupBtn"); 	// 가입하기 버튼 Element
   var signupForm = $("#signupForm"); 	// 회원가입 Form Element
   var idCheckFlag = false				// 중복확인 여부 flag
   
   // 프로필 이미지 선택 이벤트(Open파일을 통해 이미지 파일을 선택하면 이벤트 발생)
   imgFile.on("change",function(event){   // event를 변수로 받아오고
      // event안에 들어있는 여러 값들 中
      var file = event.target.files[0];   // 내가 선택한 파일(여기서는 이미지)
      
      // isImageFile이라는 메서드에 우리가 선택한 file을 집어놓고 이미지파일인지 , 아닌지를 판단
      if(isImageFile(file)){   // 이미지 파일이라면
         var reader = new FileReader();
         reader.onload = function(e){
            // 프로필 이미지 Element에 src 경로로 result를 셋팅한다.
            // 이미지 파일 데이터가 base64인코딩 형태로 변형된 데이터가 src경로에 설정된다.
            profileImg.attr("src", e.target.result);
         }
         reader.readAsDataURL(file);
      }else{   // 이미지 파일이 아니라면
         alert("이미지 파일을 선택해주세요!");
      }
   });
   
   // 중복확인 버튼 클릭 이벤트
    idCheckBtn.on("click", function() {
		console.log("중복확인 버튼 클릭...!");
		
		var id = $("#memId").val();		// 아이디 입력 값
		if(id == null || id == ""){
			alert("아이디를 입력해주세요!");
			return false;
		}
		
		// 비동기 통신, 서버로 데이터를 전송하기 위한 데이터 준비
		// JSON 객체의 형식으로 데이터를 구성한다.
		var data = {
			memId : id
		};
		
		// 데이터를 전송하는 방식이 2가지
		// 1) JSON객체 형식
		// 	- JSON.stringify(data);
		// 2) 문자열 형식
		//	- {memId : id}
		// 위 설정의 방식을 중복확인 처리 컨트롤러 메소드에서 받는 방식 2가지를 정의하도록 한다.
		
		// 비동기 통신 준비
		$.ajax({
			url : "/notice/idCheck.do",
			type : "post",
			beforeSend : function(xhr) {	// 데이터 전송 전, 헤더에 csrf 값 설정
				xhr.setRequestHeader(header, token);
			},
			data : JSON.stringify(data),
			contentType : "application/json;charset=utf-8",
			success : function(res) {
				var err = $(".error")[0];
				if(res === "NOTEXIST"){		// 아이디 사용 가능
					$(err).html("사용 가능한 아이디입니다!").css("color", "green");
					idCheckFlag = true;		// 중복확인 완료
				}else {						// 아이디 중복
					$(err).html("이미 사용중인 아이디입니다!").css("color", "red");
					idCheckFlag = false;	// 중복확인 완료
				}
			}
		});
	});
	
	// 상세주소란에서 포커스가 다른곳으로 이동되었을 때 이벤트(주소에 맞는 지도 띄우기)
    memAddress2.focusout(function() {
		var address1 = $("#memAddress1").val();	// 일반주소 데이터
		var address2 = $("#memAddress2").val();	// 상세주소 데이터
		
		// 일반주소 데이터가 존재하지 않는 경우, 지도를 띄울 수 없기 때문에 데이터 유무의 기준을 address1으로 설정
		if(address1 != null || address1 != ""){
			var mapContainer = document.getElementById('map'); // 지도를 표시할 div
			mapContainer.style.display = "block";
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 

		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address1 + " " + address2, function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });

		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">HOME</div>'
		        });
		        infowindow.open(map, marker);

		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
	}
	});
	// 가입하기 버튼 클릭 이벤트
    signupBtn.on("click", function() {
		var agreeFlag = false;				// 개인정보 동의방침 여부 falg
		var memId = $("#memId").val();		// 아이디 값
		var memPw = $("#memPw").val();		// 비밀번호 값
		var memName = $("#memName").val();	// 이름 값
		var memAgree = $("#memAgree:checked").val();	// 개인정보 동의 방침 값
		
// 	if(memId == null || memId == ""){
// 		alert("아이디를 입력해주세요!");
// 		return false;
// 	}
	
// 	if(memPw == null || memPw == ""){
// 		alert("비밀번호를 입력해주세요!");
// 		return false;
// 	}
	
// 	if(memName == null || memName == ""){
// 		alert("이름을 입력해주세요!");
// 		return false;
// 	}
	
	if(memAgree == "Y") {
		agreeFlag = true;		// 개인정보 동의 방침 체크 확인완료!
	}
	
	// 개인정보 처리방침을 동의했습니까?
	// 아이디 중복체크를 이행하고 오셨습니까?
	// 오셨다구요? 그럼 가입하기를 진행하겠습니다.
	// 예? 안했다구요? 그럼 가입하기를 할 수 없습니다.
	if(agreeFlag){
		if(idCheckFlag){
			signupForm.submit();
		}else{
			alert("아이디 중복체크를 해주세요!");
		}
	}else {
		alert("개인정보 동의를 체크해주세요!");
	}
	});
	
	
});


// 이미지 파일인지 체크
function isImageFile(file) {
	var ext = file.name.split(".").pop().toLowerCase();		// 파일명에서 확장자를 가져온다.
	return ($.inArray(ext,["jpg","jpeg","gif","png"]) === -1) ? false : true;
}

// 다음 우편번호 찾기 API
function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('memPostCode').value = data.zonecode;
                document.getElementById("memAddress1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("memAddress2").focus();
            }
        }).open();
    }

// 지도 API

</script>