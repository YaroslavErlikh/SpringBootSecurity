<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать профиль</title>
</head>
<body>

<h2>Редактировать профиль</h2>
${message}
<form action="/user/editProfileFine" method="post">
    <input type="text" name="username">
    <br>
    <input type="text" name="password">
    <br>
    <input type="submit" name="Изменить" value="Изменить">
</form>
<br>
<jsp:include page="../butons_back.jsp"/>
<br>
<jsp:include page="../logout.jsp"/>
</body>
</html>
