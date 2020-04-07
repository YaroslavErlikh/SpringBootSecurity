<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Профиль</title>
</head>
<body>
ID: ${user.id}
Имя: ${user.username}

<form action="/user/editProfile" method="get">
    <input type="submit" value="Редактировать">
</form>
<br>
<form action="/user/deleteProfile" method="get">
    <input type="submit" value="Удалить"/>
</form>
<br>
<sec:authorize access="hasAnyRole('ADMIN')">
    <form action="/admin" method="get">
        <input type="submit" value="Пользователи"/>
    </form>
</sec:authorize>

<br>
<jsp:include page="../butons_back.jsp"/>
<br>
<jsp:include page="../logout.jsp" />
</body>
</html>
