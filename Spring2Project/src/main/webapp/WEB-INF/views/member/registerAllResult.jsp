<%@page import="kr.or.ddit.vo.test.RegisterMemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	결과페이지 registerAllResult
	
	[아래 결과처럼 출력!]
	유저 ID		a001
	패스워드		1234
	이름			홍길동
	E-Mail		ddit@n.com
	생년월일		2024년 05월 17일
	성별			남자
	개발자여부		개발자
	외국인여부		내국인
	국적			대한민국
	소유차량		소유차량 없음
	취미			운동 영화
	우편번호		54678
	주소			대전광역시 중구 오류동 123
	카드1(번호)	1234-1234-1234-1234
	카드1(유효년월)	2040년 09월 09일
	카드2(번호)	1234-1234-1234-1234
	카드2(유효년월)	2040년 09월 09일
	
	조건
 -->
 <table border="1">
 	<tr>
 		<th>유저 ID</th>
 		<td>${user.userId }</td>
 	</tr>
 	<tr>
 		<th>패스워드</th>
 		<td>${user.password }</td>
 	</tr>
 	<tr>
 		<th>이름</th>
 		<td>${user.name }</td>
 	</tr>
 	<tr>
 		<th>E-Mail</th>
 		<td>${user.email }</td>
 	</tr>
 	<tr>
 		<th>생년월일</th>
 		<td><fmt:formatDate value="${user.birth}" pattern="yyyy년 MM월 dd일"/></td>
 	</tr>
 	<tr>
 		<th>성별</th>
 		<td>
 			<c:choose>
 				<c:when test="${user.gender == 'male' }">남자</c:when>
 				<c:when test="${user.gender == 'femal' }">여자</c:when>
 				<c:otherwise>기타</c:otherwise>
 			</c:choose>
 		</td>
 	</tr>
 	<tr>
 		<th>개발자 여부</th>
 		<td>
 			<c:choose>
 				<c:when test="${user.developer == 'Y' }">개발자</c:when>
 				<c:otherwise>일반인</c:otherwise>
 			</c:choose>
 		</td>
 	</tr>
 	<tr>
 		<th>외국인 여부</th>
 		<td>
 			<c:choose>
 				<c:when test="${user.foreigner == null }">내국인</c:when>
 				<c:otherwise>외국인</c:otherwise>
 			</c:choose>
 		</td>
 	</tr>
 	<tr>
 		<th>국적</th>
 		<td>
 			<c:forEach var="national" items="${user.national }">
 				<c:choose>
 					<c:when test="${national == 'korea' }">한국</c:when>
 					<c:when test="${national == 'germany' }">독일</c:when>
 					<c:when test="${national == 'austrailia' }">호주</c:when>
 					<c:when test="${national == 'canada' }">캐나다</c:when>
 					<c:when test="${national == 'usa' }">미국</c:when>
 				</c:choose>
 			</c:forEach>
 		</td>
 	</tr>
 	<tr>
 		<th>소유차량</th>
 		<td>
 			<c:choose>
	 			<c:when test="${empty user.cars }">소유차량 없음</c:when>
	 			<c:otherwise>
	 				<c:forEach var="car" items="${user.cars }">
	 					<c:out value="${car }"/>
	 				</c:forEach>
	 			</c:otherwise>
 			</c:choose>
 		</td>
 	</tr>
 	<tr>
 		<th>취미</th>
 		<td>
 			<c:forEach var="hobby" items="${user.hobby }">
 				<c:choose>
	 				<c:when test="${hobby == 'sports' }">운동</c:when>
	 				<c:when test="${hobby == 'music' }">음악</c:when>
	 				<c:when test="${hobby == 'movie' }">영화</c:when>
 				</c:choose>
 			</c:forEach>
 		</td>
 	</tr>
 	<tr>
 		<th>우편번호</th>
 		<td>${user.address.postCode }</td>
 	</tr>
 	<tr>
 		<th>주소</th>
 		<td>${user.address.location }</td>
 	</tr>
 	<c:forEach var="card" items="${user.cardList }" varStatus="status">
	 	<tr>
	 		<th>카드${status.index + 1 }(번호)</th>
	 		<td>${card.no }</td>
	 	</tr>
	 	<tr>
	 		<th>카드${status.index + 1 }(유효년월)</th>
	 		<td><fmt:formatDate value="${card.validMonth }" pattern="yyyy년 MM월 dd일"/></td>
	 	</tr>
 	</c:forEach>
 	<tr>
 		<th>소개</th>
 		<td>${user.introduction }</td>
 	</tr>
 </table>
</body>
</html>