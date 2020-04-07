<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete user - ${user.id}</title>
</head>
<body>
<h3>Удалить пользователя - </h3>
<br>id - ${user.id}
<br>Имя - ${user.username}
<br>Пароль - ${user.password}
<br>Права -
<c:forEach var="roles" items="${user.roles}">
    ${roles.name}<br>
</c:forEach>

<br>
Вы уверены?
<c:url value="/admin/deleteUser/${user.id}" var="var"/>
<form action="${var}" method="get">
    <input type="hidden" name="id" value="${user.id}">
    <input type="submit" value="Удалить">
</form>

<br>
<jsp:include page="../butons_back.jsp"/>
<br>
<jsp:include page="../logout.jsp"/>

</body>
</html>
