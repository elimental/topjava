<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ldtf" uri="/WEB-INF/date_format" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h1>Meals</h1>
<table border="1">
    <tr>
        <td>Дата</td>
        <td>Описание</td>
        <td>Кол-во калорий</td>
        <td></td>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr ${meal.excess ? 'bgcolor="red"' : ''}>
        <td>${ldtf:formatLocalDateTime(meal.dateTime)}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td>
            <a href="${pageContext.request.contextPath}/meals?update&id=${meal.id}">Редактировать</a>
            <a href="${pageContext.request.contextPath}/meals?remove&id=${meal.id}">Удалить</a>
        </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="${pageContext.request.contextPath}/meals?add"><button>Добавить</button></a>
</body>
</html>
