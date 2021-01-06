<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List of Categories</title>
</head>
<body>
<h1>List of Categories</h1>
<hr>

<button type="button"
        onclick="window.location.href = 'http://localhost:8080/Rzv_war/categories/add'">Add category
</button>

<table>
    <tr>
        <th>Name</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${categories}" var="category">
        <tr>
            <td>${category.name}</td>
            <td>
                <div class="btn-group" role="group">
                    <button type="button"
                            onclick="window.location.href='http://localhost:8080/Rzv_war/categories/update/${category.id}'">
                        Update
                    </button>
                    <button type="button"
                            onclick="window.location.href='http://localhost:8080/Rzv_war/categories/delete/${category.id}'">
                        Delete
                    </button>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

<button type="button"
        onclick="window.location.href = 'http://localhost:8080/Rzv_war/products'">Go to products page
</button>
<button onclick="location.href = '${pageContext.request.contextPath}'">Back</button>

</body>
</html>
