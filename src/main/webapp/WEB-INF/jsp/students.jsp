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
    <title>Студенты</title>
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

    <div class="students-list">
        <c:if test="${role == 1}">
                <span>
                    <form action="/student-create" method="get">
                        <input class="createStudent" type="submit" value="Создать студента">
                    </form>
                </span><br>
            <span>
                <input class="modifyStudents" type="submit" value="Модифицировать выбранного студента"
                       onclick="modifyStudent()">
            </span><br>
            <span>
                <input class="deleteStudents" type="submit" value="Удалить выбранных студентов"
                       onclick="deleteStudent()">
            </span>
        </c:if>
    </div>

    <h3 class="listOfSomething">Список студентов</h3>
    <div class="scroll">
        <table class="tableStudents">
            <tr>
                <c:if test="${role == 1}">
                <th></th>
                </c:if>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Группа</th>
                <th class="joinDate">Дата поступления (гггг-мм-дд)</th>
            </tr>

            <c:forEach items="${students}" var="st">
                <tr>
                    <c:if test="${role == 1}">
                    <td class="checks"><input type="checkbox" value="${st.id}"></td>
                    </c:if>
                    <td>${st.surname}</td>
                    <td>${st.name}</td>
                    <td>${st.group}</td>
                    <td>${st.date}</td>
                </tr>
            </c:forEach>
        </table>

        <form id="deleteStudentForm" action="/students" method="post">
            <input id="deleteStudentHidden" type="hidden" name="deleteStudentHidden">
        </form>

        <form id="modifyStudentForm" action="/student-modify" method="get">
            <input id="modifyStudentHidden" type="hidden" name="modifyStudentHidden">
        </form>
    </div>
</div>
</body>
</html>