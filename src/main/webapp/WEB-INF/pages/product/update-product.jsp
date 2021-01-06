<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<h1>Update Product</h1>
<hr>

<form:form method="PUT" action="http://localhost:8080/Rzv_war/products/update/${product.id}" modelAttribute="product">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="categoryName">Category</form:label></td>
            <td><form:input path="categoryName"/></td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td><form:label path="price">Price</form:label></td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td><form:label path="stock">Stock</form:label></td>
            <td><form:input path="stock"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
