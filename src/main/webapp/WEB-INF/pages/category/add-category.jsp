<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Category</title>
</head>
<body>
<h1>Add Category</h1>
<hr>

<form:form method="POST" action="http://localhost:8080/Rzv_war/categories/add" modelAttribute="category">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/><label>${name_error}</label></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
            <td><label>${status}</label></td>
        </tr>
    </table>
</form:form>
<button onClick="location.href = '${pageContext.request.contextPath}/categories'">Back to Categories</button>
</body>
</html>