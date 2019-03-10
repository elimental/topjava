<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update meal</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/meals" method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <input type="datetime-local" name="date_time" value="${meal.dateTime}"><br><br>
    <input type="text" name="description" value="${meal.description}"><br><br>
    <input type="text" name="calories" value="${meal.calories}"><br><br>
    <button type="submit">Изменить</button>
</form>
</body>
</html>
