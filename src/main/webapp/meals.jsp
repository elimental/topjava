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
    </tr>
    <c:forEach var="meal" items="${meals}">
        <c:choose>
            <c:when test="${meal.excess}">
                <tr bgcolor="red">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
        <td>
            <a href="${pageContext.request.contextPath}/updateMeal?id=${meal.id}">${ldtf:formatLocalDateTime(meal.dateTime, "dd.MM.yyyy HH:mm")}</a>
        </td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
