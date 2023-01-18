<%@ page import="java.util.List, classes.Shop" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Servlet</title>
</head>
<body>
    <jsp:include page="header.html"/>

    <h2>Products List</h2>
    <table>
        <tr><th>Name</th>
        <c:forEach var="shop" items="${items}">
            <tr><td>${shop}</td>
        </c:forEach>
    </table>
    <button><a href="addItem.jsp">Добавить продукт</a></button>
    <jsp:include page="footer.jsp"/>
</body>
</html>
