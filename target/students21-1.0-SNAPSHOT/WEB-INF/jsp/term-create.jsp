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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="/resources/js/Function.js"></script>
    <title>Создать семестр</title>
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
                <a class="title-logout" href="/login">Войти</a>
            </c:otherwise>
        </c:choose>
    </header>

    <a class="to-main-page" href="/">На главную</a>
    <a class="to-main-page" href="/terms">Назад</a>

    <h4 class="sign">Чтобы создать семестр, заполните поля, выберите дисциплины и нажмите кнопку "Создать"</h4>
    <form id="createTermForm" method="post" action="/term-create">
        <label>Семестр №:</label><br>
        <input class="durationTermCreate" type="text" name="name"><br>
        <label>Длительность (в неделях):</label><br>
        <input class="durationTermCreate" type="text" name="duration"><br>
        <h3 class="listOfSomething">Дисциплины в семестре</h3>
        <div class="scroll">
            <table class="tableTerms">
                <c:forEach items="${disciplines}" var="d">
                    <tr class="tableTermsSticky">
                        <td class="checks"><input type="checkbox" value="${d.id}"></td>
                        <td class="nameTerm">${d.discipline}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <input id="createTermHidden" type="hidden" name="createTermHidden">
        <input class="termButton" type="submit" value="Создать" onclick="createTerm()">
    </form>

</div>
</body>
</html>