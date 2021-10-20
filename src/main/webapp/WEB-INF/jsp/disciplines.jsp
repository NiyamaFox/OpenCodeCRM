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
    <title>Дисциплины</title>
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
                <form action="/discipline-create" method="get">
                    <input class="createDiscipline" type="submit" value="Создать дисциплину"><br>
                </form>
            </span>
            <span>
                <input class="modifyDisciplines" type="submit" value="Модифицировать выбранную дисциплину"
                       onclick="modifyDiscipline()"><br>
            </span>
            <span>
                <input class="deleteDisciplines" type="submit" value="Удалить выбранные дисциплины"
                       onclick="deleteDiscipline()">
            </span>
        </c:if>
    </div>

    <h3 class="listOfSomething">Список дисциплин</h3>
    <div class="scroll">
        <table class="tableDisciplines">
            <tr>
                <c:if test="${role == 1}">
                    <th></th>
                </c:if>
                <th class="nameDiscipline">Наименование дисциплины</th>
            </tr>

            <c:forEach items="${disciplines}" var="d">
                <tr>
                    <c:if test="${role == 1}">
                        <td class="checks"><input type="checkbox" value="${d.id}"></td>
                    </c:if>
                    <td>${d.discipline}</td>
                </tr>
            </c:forEach>
        </table>

        <form action="/disciplines" method="post" id="deleteDisciplineForm">
            <input id="deleteDisciplineHidden" type="hidden" name="deleteDisciplineHidden">
        </form>

        <form action="/discipline-modify" method="get" id="modifyDisciplineForm">
            <input type="hidden" id="modifyDisciplineHidden" name="modifyDisciplineHidden">
        </form>
    </div>
</div>
</body>
</html>