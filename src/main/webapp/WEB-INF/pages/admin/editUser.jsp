<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit user - ${userEditing.id}</title>
</head>
<body>
<h3>${pageContext.request.userPrincipal.name}</h3>
<br>
<h5>Editing user - ${userEditing.id}</h5>

<tf:form action="/admin/edit" method="post" modelAttribute="userEditing">

    <input type="hidden" name="id" value="${userEditing.id}">

    Имя:
    <input type="text" name="username" value="${userEditing.username}">
    ${message}
    <br>
    Пароль:
    <input type="text" name="password" value="${userEditing.password}">
    <br>

    <tf:checkboxes path="roles" items="${rolelist}" element="li" itemValue="name" itemLabel="name"/>

    <br>
    <input type="submit" value="Изменить">
</tf:form>
<br>
<jsp:include page="../butons_back.jsp"/>
<br>
<jsp:include page="../logout.jsp"/>

</body>
</html>
