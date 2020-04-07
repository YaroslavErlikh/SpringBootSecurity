<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Главная</title>
</head>
<body>
<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <form action="/login" method="get">
            <input type="submit" value="Войти">
        </form>
        <form action="/registration" method="get">
            <input type="submit" value="Регистрация">
        </form>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <form action="/user/profile" method="get">
            <input type="submit" value="Профиль">
        </form>
        <br>
        <jsp:include page="logout.jsp" />
    </sec:authorize>
</div>
</body>
</html>