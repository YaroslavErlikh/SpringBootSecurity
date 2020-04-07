<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<c:url value="/admin/add" var="var"/>
<form action="${var}" method="post">
    <label for="name">Имя:</label>
    <input type="text" name="username" id="name" required>
    <br>
    <label for="pass">Пароль:</label>
    <input type="text" name="password" id="pass" required>
    <br>
    <input type="submit" value="Добавить">
</form>
<br>
<jsp:include page="../butons_back.jsp"/>
<br>
<jsp:include page="../logout.jsp"/>

</body>
</html>
