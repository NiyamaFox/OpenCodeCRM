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
                <a href="/login">Войти</a>
            </c:otherwise>
        </c:choose>
    </header>

    <a class="to-main-page" href="/index.jsp">На главную</a><br>

    <span>Список дисциплин</span>
        <table>
            <tr>
                <th></th>
                <th>Наименование дисциплины</th>
            </tr>

            <c:forEach items="${disciplines}" var="d">
                <tr>
                    <c:if test="${role == 1}">
                        <td><input type="checkbox" value="${d.id}"></td>
                    </c:if>
                    <td>${d.discipline}</td>
                </tr>
            </c:forEach>
        </table>

    <div class="stud-list-page">
        <ul class="students-list">
            <c:if test="${role == 1}">
                <li>
                    <div>
                        <form action="/discipline-create" method="get">
                            <input type="submit" value="Создать дисциплину">
                        </form>
                    </div>
                </li>
                <li>
                    <div>
                        <input type="submit" value="Модифицировать выбранную дисциплину" onclick="modifyDiscipline()">
                    </div>
                </li>
                <li>
                    <div>
                        <input type="submit" value="Удалить выбранную дисциплину" onclick="">
                    </div>
                </li>
            </c:if>
        </ul>
    </div>

    <form action="/discipline-modify" method="get" id="modifyDisciplineForm">
        <input type="hidden" id="modifyDisciplineHidden" name="modifyDisciplineHidden">
    </form>

</div>
</body>
</html>