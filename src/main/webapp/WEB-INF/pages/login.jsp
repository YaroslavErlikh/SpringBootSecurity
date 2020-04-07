<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
  <% response.sendRedirect("/"); %>
</sec:authorize>
<form action="/login" method="post">
  Имя: <input type="text" name="username" placeholder="Имя" required> <br>
  Пароль: <input type="text" name="password" placeholder="Пароль" required><br>
  <input type="submit" value="Авторизация">
</form>
<br>
<jsp:include page="butons_back.jsp"/>

</body>
</html>
