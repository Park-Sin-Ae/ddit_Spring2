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
   <h2>유저 등록 화면</h2>
   <hr><br>
   <form:form method="post" action="/validation/result" modelAttribute="member"> 
      <table border="1">
         <tr>
            <td>유저ID</td>
            <td>
               <form:input path="userId"/>
               <font color="red">
                  <form:errors path="userId"/>
               </font>
            </td>
         </tr>
         <tr>
            <td>이름</td>
            <td>
               <form:input path="userName"/>
               <font color="red">
                  <form:errors path="userName"/>
               </font>
            </td>
         </tr>
         <tr>
            <td>패스워드</td>
            <td>
               <form:input path="password"/>
               <font color="red">
                  <form:errors path="password"/>
               </font>
            </td>
         </tr>
         <tr>
            <td>E-Mail</td>
            <td>
               <form:input path="email"/>
               <font color="red">
                  <form:errors path="email"/>
               </font>
            </td>
         </tr>
         <tr>
            <td>성별</td>
            <td>
               <form:radiobutton path="gender" value="male" label="Male"/>
               <form:radiobutton path="gender" value="female" label="Female"/>
               <form:radiobutton path="gender" value="other" label="Other"/>
            </td>
         </tr>
      
      </table>
      <form:button name="register">등록</form:button>
   </form:form>
</body>
</html>