<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h2>Spring Form</h2>
   <p>1) 모델에 기본 생성자로 생성한 폼 객체를 추가한 후에 화면에 전달한다.</p>

   <form:form action="/formtag/register" method="post" modelAttribute="member">
      <table border="1">
         <tr>
            <td>소개</td>
            <td>
               <form:textarea path="introduction" rows="10" cols="30"/>
            </td>
         </tr>
      </table>
   <form:button name="register">등록</form:button>
   </form:form>
</body>
</html>