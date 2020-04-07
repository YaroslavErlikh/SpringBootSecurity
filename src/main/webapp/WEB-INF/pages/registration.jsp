<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form action="/registration" method="post">
    Имя:<input type="text" name="username" placeholder="Имя пользователя" required/>
    <br>
    Пароль:<input type="text" name="password" placeholder="Пароль" required/>
    <br>
    Подтверждение пароля:<input type="text" name="passwordConfirm" placeholder="Подтверждение пароля" required/>
    <br>
    <input type="submit" value="Зарегистрироваться"/>
</form>

</body>
</html>
