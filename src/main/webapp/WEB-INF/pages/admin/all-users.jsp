<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <title>List of Users</title>
</head>
<body>

<h1>List of Admins</h1>
<hr>

<table>
    <tr>
        <th>E-mail</th>
        <th>Role</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Country</th>
        <th>City</th>
        <th>County</th>
        <th>Street Name</th>
        <th>Street Number</th>
        <th>Flat Number</th>
        <th>Building Number</th>
        <th>Actions</th>
    </tr>

    <tbody>
    <c:forEach items="${admins}" var="admin">
        <tr>
            <td><c:out value="${admin.email}"/></td>
            <td><c:out value="${admin.role}"/></td>
            <td><c:out value="${admin.firstName}"/></td>
            <td><c:out value="${admin.lastName}"/></td>
            <td><c:out value="${admin.phoneNumber}"/></td>
            <td><c:out value="${admin.country}"/></td>
            <td><c:out value="${admin.city}"/></td>
            <td><c:out value="${admin.county}"/></td>
            <td><c:out value="${admin.streetName}"/></td>
            <td><c:out value="${admin.streetNumber}"/></td>
            <td><c:out value="${admin.flatNumber}"/></td>
            <td><c:out value="${admin.buildingNumber}"/></td>
            <td>
                <div>
                    <button
                            onClick="location.href = 'http://localhost:8080/Rzv_war/admin/update-user/${admin.email}'">
                        Update
                    </button>
                    <button
                            onClick="location.href = 'http://localhost:8080/Rzv_war/admin/delete-account/${admin.email}'">
                        Delete
                    </button>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <button
            onclick="location.href = 'http://localhost:8080/Rzv_war/admin/add-admin'">Add an admin account
    </button>
</div>
<br><br><br>
<h1>List of Customers</h1>
<hr>

<table>
    <tr>
        <th>E-mail</th>
        <th>Role</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Country</th>
        <th>City</th>
        <th>County</th>
        <th>Street Name</th>
        <th>Street Number</th>
        <th>Flat Number</th>
        <th>Building Number</th>
        <th>Actions</th>
    </tr>

    <tbody>
    <c:forEach items="${users}" var="customer">
        <tr>
            <td><c:out value="${customer.email}"/></td>
            <td><c:out value="${customer.role}"/></td>
            <td><c:out value="${customer.firstName}"/></td>
            <td><c:out value="${customer.lastName}"/></td>
            <td><c:out value="${customer.phoneNumber}"/></td>
            <td><c:out value="${customer.country}"/></td>
            <td><c:out value="${customer.city}"/></td>
            <td><c:out value="${customer.county}"/></td>
            <td><c:out value="${customer.streetName}"/></td>
            <td><c:out value="${customer.streetNumber}"/></td>
            <td><c:out value="${customer.flatNumber}"/></td>
            <td><c:out value="${customer.buildingNumber}"/></td>
            <td>
                <div>
                    <button
                            onClick="location.href = 'http://localhost:8080/Rzv_war/admin/update-user/${customer.email}'">
                        Update
                    </button>
                    <button
                            onClick="location.href = 'http://localhost:8080/Rzv_war/admin/delete-account/${customer.email}'">
                        Delete
                    </button>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <button
            onclick="location.href = 'http://localhost:8080/Rzv_war/admin/create-customer-account'">Add an customer account
    </button>
    <button type="button" name="back" onclick="location.href = '${pageContext.request.contextPath}'">Back to main page</button>
</div>
</body>
</html>
