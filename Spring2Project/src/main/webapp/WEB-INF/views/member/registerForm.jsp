<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>REGISTER FORM</h1>
	<hr/>
	
	<p>1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.</p>
	<a href="/register?userId=hongkd&password=1234">BUTTON01</a>
	
	<p>2) URL 경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다.</p>
	<a href="/register/hongkd">BUTTON02</a>
	
	<p>
		3) HTML Form 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
		그리고, 매개변수의 위치는 순서에 상관없고 오직 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	</p>
	<form action="/register01" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		<input type="submit" value="전송"/>
	</form>
	
	<p>4) HTML Form 필드값이 숫자일 경우에는 숫자로 타입변환하여 데이터를 취득할 수 있다.</p>
	<form action="/register02" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/>
	</form>
	<br/><hr/>
	
	<h4>3. 요청 데이터 처리 어노테이션</h4>
	<p>
		1) URL 경로 상의 경로 변수가 여러 개일 때 @PathVariable 어노테이션을 사용하여
		특정한 경로 변수명을 지정해준다.
	</p>
	<a href="/register/hongkd/100">BUTTON03</a><br/>
	
	<p>2) @RequestParam 어노테이션을 사용하여 특정한 HTML Form의 필드명을 지정하여 요청을 처리한다.</p>
	<form action="/register0202" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/>
	</form>
	<br/><hr/>
	
	<h4>4. 요청 처리 자바빈즈</h4>
	<p>1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.</p>
	<form action="/beans/register01" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/>
	</form>
	
	<p>2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수와 기본 데이터 타입인 정수 타입 매개 변수로 처리한다.</p>
	<form action="/beans/register02" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		password : <input type="text" name="password" value="1234"/><br/>
		coin : <input type="text" name="coin" value="100"/><br/>
		<input type="submit" value="전송"/>
	</form>
	<br/><hr/>
	
	<h4>5. Date 타입 처리</h4>
	<p>1) 쿼리 파라미터(dateOfBirth=1234)로 전달받은 값이 날짜 문자열 형식으로 들어올까?</p>
	<a href="/registerByGet01?userId=hongkd&&dateOfBirth=1234">BUTTON01</a>
	<br/>
	
	<p>2) 쿼리 파라미터(dateOfBirth=2024-05-16)로 전달받은 값이 날짜 문자열 형식으로 들어올까?</p>
	<a href="/registerByGet01?userId=hongkd&&dateOfBirth=2024-05-16">BUTTON02</a>
	<br/>
	
	<p>3) 쿼리 파라미터(dateOfBirth=20240516)로 전달받은 값이 날짜 문자열 형식으로 들어올까?</p>
	<a href="/registerByGet01?userId=hongkd&&dateOfBirth=20240516">BUTTON03</a>
	<br/>
	
	<p>4) 쿼리 파라미터(dateOfBirth=2024/05/16)로 전달받은 값이 날짜 문자열 형식으로 들어올까?</p>
	<a href="/registerByGet01?userId=hongkd&&dateOfBirth=2024/05/16">BUTTON04</a>
	<br/>
	<font color="green">SUCCESS</font>
	<br/>
	
	<p>
		5) Member 매개변수와 쿼리 파라미터(dateOfBirth=20240516)로 전달받은
		값이 날짜 문자열 형식으로 설정 시, Date 타입으로 받을 수 있는가?
	</p>
	<a href="/registerByGet02?userId=hongkd&&dateOfBirth=2024/05/16">BUTTON05</a>
	<br/><hr/>
	
	<h4>6. @DateTimeFormat 어노테이션</h4>
	<p>
		1) Member 매개변수와 폼 방식 요청 전달받은 값이 날짜 문자열 형식으로 설정 시,
	</p>
	<form action="/registerByGet03" method="post">
		userId : <input type="text" name="userId" value="hongkd"/><br/>
		dateOfBirth : <input type="text" name="dateOfBirth" value=""/><br/>
		<input type="submit" value="전송"/>
	</form>
	<h4>7. 폼 방식 요청 처리</h4>
  	 <p>1) 폼 텍스트 필드 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
   <form action="/registerUserId" method="post">
      userId : <input type="text" name="userId"/><br/>
      <input type="submit" value="전송"/>
   </form>
   
	<p>2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.</p>
   <form action="/registerMemberUserId" method="post">
      userId : <input type="text" name="userId"/><br/>
      <input type="submit" value="전송"/>
   </form>
   
   <p>3) 폼 비밀번호 필드 요소값을 자바빈즈 매개변수로 처리한다.</p>
   <form action="/registerPassword" method="post">
      password : <input type="password" name="password"/><br/>
      <input type="submit" value="전송"/>
   </form>
   
   <p>4) 폼 라디오버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
   <form action="/registerRadio" method="post">
   	  gender : <br/>
   	  <input type="radio" name="gender" value="male" checked>Male<br/>
   	  <input type="radio" name="gender" value="female">FeMale<br/>
   	  <input type="radio" name="gender" value="other">Other<br/>
      <input type="submit" value="전송"/>
   </form>
   
   <p>5) 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
   <form action="/registerSelect" method="post">
   	  nationality : <br/>
   	  <select name="nationality">
   	  		<option value="korea">대한민국</option>
   	  		<option value="germany">독일</option>
   	  		<option value="austrailia">호주</option>
   	  		<option value="canada">캐나다</option>
   	  		<option value="usa">미국</option>
   	  </select>
      <input type="submit" value="전송"/>
   </form>
   
   <p>6) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
   <form action="/registerMultiSelect01" method="post">
   	  car : <br/>
   	  <select name="cars" multiple="multiple">
   	  		<option value="jeep">JEEP</option>
   	  		<option value="volvo">VOLVO</option>
   	  		<option value="bmw">BMW</option>
   	  		<option value="audi">AUDI</option>
   	  </select>
      <input type="submit" value="전송"/>
   </form>
   
   <p>7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.</p>
   <form action="/registerMultiSelect02" method="post">
   	  carArray : <br/>
   	  <select name="carArray" multiple="multiple">
   	  		<option value="jeep">JEEP</option>
   	  		<option value="volvo">VOLVO</option>
   	  		<option value="bmw">BMW</option>
   	  		<option value="audi">AUDI</option>
   	  </select>
      <input type="submit" value="전송"/>
   </form>
   
   <p>8) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
   <form action="/registerMultiSelect03" method="post">
   	  carList : <br/>
   	  <select name="carList" multiple="multiple">
   	  		<option value="jeep">JEEP</option>
   	  		<option value="volvo">VOLVO</option>
   	  		<option value="bmw">BMW</option>
   	  		<option value="audi">AUDI</option>
   	  </select>
      <input type="submit" value="전송"/>
   </form>
   
   <p>9) 폼 체크박스 요소 값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
   <form action="/registerCheckbox01" method="post">
   		hobby : <br/>
   		<input type="checkbox" name="hobby" value="sports">Sports<br/>
   		<input type="checkbox" name="hobby" value="music">Music<br/>
   		<input type="checkbox" name="hobby" value="movie">Movie<br/>
   		<input type="submit" value="전송"/>
   </form>
   
   <p>10) 폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리한다.</p>
   <form action="/registerCheckbox02" method="post">
   		hobby : <br/>
   		<input type="checkbox" name="hobbyArray" value="sports">Sports<br/>
   		<input type="checkbox" name="hobbyArray" value="music">Music<br/>
   		<input type="checkbox" name="hobbyArray" value="movie">Movie<br/>
   		<input type="submit" value="전송"/>
   </form>
   
   <p>11) 폼 체크박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.</p>
   <form action="/registerCheckbox03" method="post">
   		hobby : <br/>
   		<input type="checkbox" name="hobbyList" value="sports">Sports<br/>
   		<input type="checkbox" name="hobbyList" value="music">Music<br/>
   		<input type="checkbox" name="hobbyList" value="movie">Movie<br/>
   		<input type="submit" value="전송"/>
   </form>
   
   <p>12) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.</p>
   <form action="/registerCheckbox04" method="post">
   		developer : <br/>
   		<input type="checkbox" name="developer" value="Y">개발자 여부<br/>
   		<input type="submit" value="전송"/>
   </form>
   
   <p>13) 폼 체크박스 요소값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.</p>
   <form action="/registerCheckbox05" method="post">
   		foreigner : <br/>
   		<input type="checkbox" name="foreigner" value="Y">외국인 여부<br/>
   		<input type="submit" value="전송"/>
   </form>
   
   <p>14) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.</p>
   <form action="/registerAddress" method="post">
   		postCode : <input type="text" name="postCode"><br/>
   		location : <input type="text" name="location"><br/>
   		<input type="submit" value="전송"/>
   </form>
   
   <p>15) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.</p>
   <form action="/registerUserAddress" method="post">
   		postCode : <input type="text" name="address.postCode"><br/>
   		location : <input type="text" name="address.location"><br/>
   		<input type="submit" value="전송"/>
   </form>
   
   <p>16) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.</p>
   <form action="/registerUserCardList" method="post">
   		카드1 - 번호 : <input type="text" name="cardList[0].no"><br/>
   		카드1 - 유효년월 : <input type="text" name="cardList[0].validMonth"><br/>
   		카드2 - 번호 : <input type="text" name="cardList[1].no"><br/>
   		카드2 - 유효년월 : <input type="text" name="cardList[1].validMonth"><br/>
   		<input type="submit" value="전송"/>
   </form>
   <br/><hr/>
   
   <h3>8. 파일업로드 폼 방식 요청 처리</h3>
   <p>
   		3) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.
   </p>
   <form action="/registerFile03" method="post" enctype="multipart/form-data">
      userId : <input type="text" name="userId"/><br/>
      password : <input type="text" name="password"/><br/>
      <input type="file" name="picture"/>
      <input type="submit" value="전송"/>
   </form>
   
   <p>4) 파일 업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리한다.</p>
   <form action="/registerFile04" method="post" enctype="multipart/form-data">
      userId : <input type="text" name="userId"/><br/>
      password : <input type="text" name="password"/><br/>
      <input type="file" name="picture"/>
      <input type="submit" value="전송"/>
   </form>
   
   <p>5) 여러개의 파일업로드를 폼 파일 요소값을 여러 개의 MultipartFile 매개변수로 처리한다.</p>
   <form action="/registerFile05" method="post" enctype="multipart/form-data">
      userId : <input type="text" name="userId"/><br/>
      password : <input type="text" name="password"/><br/>
      <input type="file" name="picture"/>
      <input type="file" name="picture2"/>
      <input type="submit" value="전송"/>
   </form>
   
   <p>
   		6) 여러개의 파일업로드를 폼 파일 요소값을 MultipartFile 타입의 요소를 가진 리스트 컬렉션 타입
   		매개변수로 처리한다.
   </p>
   <form action="/registerFile06" method="post" enctype="multipart/form-data">
      userId : <input type="text" name="userId"/><br/>
      password : <input type="text" name="password"/><br/>
      <input type="file" name="pictureList[0]"/>
      <input type="file" name="pictureList[1]"/>
      <input type="submit" value="전송"/>
   </form>
   
   <p>
   		7) 여러개의 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultiFileMember 타입의 자바빈즈
   		매개변수로 처리한다.
   </p>
   <form action="/registerFile07" method="post" enctype="multipart/form-data">
      userId : <input type="text" name="userId"/><br/>
      password : <input type="text" name="password"/><br/>
      <input type="file" name="pictureList[0]"/>
      <input type="file" name="pictureList[1]"/>
      <input type="submit" value="전송"/>
   </form>
   
   <p>8) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 타입의 배열 매개변수로 처리한다.</p>
   <form action="/registerFile08" method="post" enctype="multipart/form-data">
      userId : <input type="text" name="userId"/><br/>
      password : <input type="text" name="password"/><br/>
      <input type="file" name="pictureArray" multiple/>
      <input type="submit" value="전송"/>
   </form>
</body>
</html>