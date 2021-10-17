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
    <script type="text/javascript" src="/resources/js/Function.js"></script>
    <title>Семестры</title>
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

    <a class="to-main-page" href="/">На главную</a><br>

    <div class="students-list">
        <c:if test="${role == 1}">
            <span>
                <form action="/term-create" method="get">
                    <input class="createTerm" type="submit" value="Создать семестр">
                </form>
            </span><br>
            <span>
                <input class="modifyTerm" type="submit" value="Модифицировать текущий семестр" onclick="modifyTerm()">
            </span><br>
            <span>
                <input class="deleteTerm" type="submit" value="Удалить текущий семестр" onclick="deleteTerm()">
            </span>
        </c:if>
    </div>

    <form action="/terms" method="get">
        <label>Выбрать семестр</label>
        <select class="termSelect" name="idSelected">
            <c:forEach items="${terms}" var="t">
                <c:choose>
                    <c:when test="${t.id == selectedTerm.id}">
                        <option selected value="${t.id}">${t.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${t.id}">${t.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <input class="justEnterTerms" type="submit" value="Выбрать">
    </form><br>

    <label>Длительность семестра: ${selectedTerm.duration}</label>

    <h3 class="listOfSomething">Список дисциплин семестра</h3>

    <div class="scroll">
        <table class="tableTerms">
            <tr>
                <th class="nameTerm">Наименование дисциплины</th>
            </tr>
            <c:forEach items="${disciplines}" var="d">
                <tr>
                    <td>${d.discipline}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="/terms" method="post" id="deleteTermForm">
            <input id="deleteTermHidden" type="hidden" name="deleteTermHidden">
        </form>

        <form action="/term-modify" method="get" id="modifyTermForm">
            <input type="hidden" id="modifyTermHidden" name="modifyTermHidden">
        </form>
    </div>

</div>
</div>
</body>
</html>