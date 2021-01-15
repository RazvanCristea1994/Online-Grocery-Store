<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<h1>Add Product</h1>
<hr>

<form:form method="POST" action="http://localhost:8080/Rzv_war/products/add" modelAttribute="product">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/><label>${name_error}</label></td>
        </tr>
        <tr>
            <td><form:label path="categoryName">Category</form:label></td>
            <td><form:input path="categoryName"/><label>${category_error}</label></td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description"/><label>${description_error}</label></td>
        </tr>
        <tr>
            <td><form:label path="price">Price</form:label></td>
            <td><form:input path="price"/><label>${price_error}</label></td>
        </tr>
        <tr>
            <td><form:label path="stock">Stock</form:label></td>
            <td><form:input path="stock"/><label>${stock_error}</label></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
            <td><label>${status}</label></td>
        </tr>
    </table>
</form:form>
</body>
</html>