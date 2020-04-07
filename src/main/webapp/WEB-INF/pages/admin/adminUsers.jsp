<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>
<h2>Users</h2>

<table border="2">
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>Пароль</th>
        <th>Права</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>
                <c:forEach var="roles" items="${user.roles}">
                    ${roles.name}<br>
                </c:forEach>
            </td>
            <td>
                <form action="admin/editUser/${user.id}" method="get">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Редактировать" style="float: left"/>
                </form>
                <form action="admin/deleteUser/${user.id}" method="post">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Удалить" style="float: left"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="admin/add" method="get">
    <input type="submit" value="Добавить пользователя"/>
</form>
<br>
<jsp:include page="../butons_back.jsp"/>
<br>
<jsp:include page="../logout.jsp"/>

</body>
</html>
