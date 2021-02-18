<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List of Categories</title>
</head>
<body>
<h1>List of Categories</h1>
<hr>

<sec:authorize access="hasAuthority('ADMIN')">
    <button type="button" onclick="window.location.href = 'http://localhost:8080/Rzv_war/categories/add'">Add category</button>
</sec:authorize>

<table>
    <tr>
        <th>Name</th>
        <sec:authorize access="hasAuthority('ADMIN')">
            <th>Actions</th>
        </sec:authorize>
    </tr>

    <c:forEach items="${categories}" var="category">
        <tr>
            <td>${category.name}</td>
            <sec:authorize access="hasAuthority('ADMIN')">
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
            </sec:authorize>
        </tr>
    </c:forEach>
</table>

<button type="button"
        onclick="window.location.href = '${pageContext.request.contextPath}/products'">Go to products page
</button>
<button type="button" name="back" onclick="location.href = '${pageContext.request.contextPath}'">Back to main page</button>
</body>
</html>
