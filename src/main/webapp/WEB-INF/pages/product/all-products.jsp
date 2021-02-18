<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List of Products</title>
</head>
<body>
<h1>List of Products</h1>
<hr>
<sec:authorize access="hasAuthority('ADMIN')">
    <button type="button" onclick="window.location.href = 'http://localhost:8080/Rzv_war/products/add'">Add product</button>
</sec:authorize>

<table>
    <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Price</th>
        <th>Description</th>
        <th>Stock</th>
        <sec:authorize access="hasAuthority('ADMIN')">
        <th>Actions</th>
        </sec:authorize>
    </tr>

    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.name}</td>
            <td>${product.categoryName}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td>${product.stock}</td>

            <sec:authorize access="hasAuthority('ADMIN')">
            <td>
                <div class="btn-group" role="group">
                    <button type="button"
                            onclick="window.location.href='http://localhost:8080/Rzv_war/products/update/${product.id}'">
                        Update
                    </button>
                    <button type="button"
                            onclick="window.location.href='http://localhost:8080/Rzv_war/products/delete/${product.id}'">
                        Delete
                    </button>
                </div>
            </td>
            </sec:authorize>
        </tr>
    </c:forEach>
</table>

<button type="button"
        onclick="window.location.href = 'http://localhost:8080/Rzv_war/categories'">Go to categories page
</button>
<button type="button" name="back" onclick="location.href = '${pageContext.request.contextPath}'">Back to main page</button>
</body>
</html>