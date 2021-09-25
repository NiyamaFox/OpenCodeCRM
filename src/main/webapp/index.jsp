<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/styles.css">
    <title>Главная страница</title>
</head>

<body>
<div class="wall">
    <header class="title">
        <span class="title-system">Система управления студентами и их успеваемостью</span>
        <c:choose>
            <c:when test="${isLogin == true}">
                <a class="title-logout" href="/logout">Выйти из ${login}</a>
            </c:when>
            <c:otherwise>
                <a href="/login">Войти</a>
            </c:otherwise>
        </c:choose>
    </header>

    <div class="sdt">
        <div class="sdt-item">
            <div class="sdt-stud">
                <a href="/students">Студенты</a>
            </div>
        </div>
        <div class="sdt-item">
            <div class="sdt-disc">
                <a href="/disciplines">Дисциплины</a>
            </div>
        </div>
        <div class="sdt-item">
            <div class="sdt-term">
                <a href="/terms">Семестры</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>