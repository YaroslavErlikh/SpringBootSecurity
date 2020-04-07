<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Удалить профиль</title>
</head>
<body>

<h2>Удалить профиль?</h2>

<br>Имя - ${user.username}
<br>Пароль - ${user.password}
<br>
Вы уверены?
<form action="/user/deleteProfile" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <input type="submit" value="Удалить">
</form>

<br>
<jsp:include page="../butons_back.jsp"/>
<br>
<jsp:include page="../logout.jsp"/>
</body>
</html>
