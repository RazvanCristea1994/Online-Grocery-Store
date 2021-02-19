<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Reviews for products</title>
</head>
<body>
<div>
    <div>
        <div>
            <h1>Reviews</h1>
        </div>
        <div></div>
    </div>
    <table>
        <thead>
        <tr>
            <td><b>Product ID</b></td>
            <td><b>Email</b></td>
            <td><b>Rating</b></td>
            <td><b>Text</b></td>
            <td><b>Actions</b></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reviews}" var="review">
            <tr>
                <td>${review.id}</td>
                <td>${review.email}</td>
                <td>${review.rating}</td>
                <td>${review.text}</td>
                <td>
                    <div>
                        <button type="button" data-ean="${review.reviewId}" data-bool="true">Approve</button>
                        <button type="button" data-ean="${review.reviewId}" data-bool="false">Dismiss</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
