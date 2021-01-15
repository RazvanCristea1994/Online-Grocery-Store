<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Category</title>
</head>
<body>
<h1>Update Category</h1>
<hr>

<form:form method="POST" action="http://localhost:8080/Rzv_war/categories/update/${category.id}"
           modelAttribute="category">
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
</body>
</html>
