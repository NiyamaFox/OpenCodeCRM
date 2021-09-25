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
                <a href="/login">Войти</a>
            </c:otherwise>
        </c:choose>
    </header>

    <a class="to-main-page" href="/index.jsp">На главную</a>

    <div class="stud-list-page">
        <ul class="students-list">
            <li>
                <div>
                    <input type="submit" value="Просмотреть успеваемость выбранных студентов">
                </div>
            </li>
            <c:if test="${role == 1}">
                <li>
                    <div>
                        <form action="/create-student" method="get">
                            <input type="submit" value="Создать студента">
                        </form>
                    </div>
                </li>
                <li>
                    <div>
                        <input type="submit" value="Модифицировать выбранного студента" onclick="modifyStudent()">
                    </div>
                </li>
                <li>
                    <div>
                        <input type="submit" value="Удалить выбранных студентов" onclick="deleteStudent()">
                    </div>
                </li>
            </c:if>
        </ul>
    </div>


    <div>
        <h3>Список студентов</h3>
        <table>
            <tr>
                <th></th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Группа</th>
                <th>Дата поступления</th>
            </tr>

            <c:forEach items="${students}" var="st">
                <tr>
                    <td><input type="checkbox" value="${st.id}"></td>
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