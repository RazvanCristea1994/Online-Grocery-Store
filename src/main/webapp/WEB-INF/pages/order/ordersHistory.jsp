<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <th>Order Id</th>
    <th>Order date</th>
    <th>Total cost</th>
    <c:forEach items="${ordersHistory}" var="ordersHistory">
        <tr>
            <td><c:out value="${ordersHistory.id}"/></td>
            <td><c:out value="${ordersHistory.orderDate}"/></td>
            <td><c:out value="${ordersHistory.totalCost}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
