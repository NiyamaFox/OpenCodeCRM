<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/styles.css">

    <title>Создать студента</title>
</head>

<body>
<div class="wall">
    <header class="title">
        <span class="title-system">Система управления студентами и их успеваемостью</span>
    </header>

    <a class="to-main-page" href="/">На главную</a>

    <h3>Вход в систему: </h3>
    <form method="post" action="/login">
        <label>Логин</label> <input type="text" name="login">
        <label>Пароль</label> <input type="text" name="password">
        <label>Выберите роль: </label>
        <select name="role">
            <option value="1">Администратор</option>
            <option value="2">Учитель</option>
            <option value="3">Студент</option>
        </select>
        <input type="submit" value="Войти">

        <c:if test="${error eq 1}">
            <h4>Поля не должны быть пустыми!</h4>
        </c:if>

        <c:if test="${error eq 2}">
            <h4>Такой пользователь не найден!</h4>
        </c:if>

    </form>
</div>
</body>
</html>