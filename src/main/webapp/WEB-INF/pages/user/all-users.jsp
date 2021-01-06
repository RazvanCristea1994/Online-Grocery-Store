<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <title>List of Users</title>
</head>
<body>
<h1>List of Users</h1>
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
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.phoneNumber}"/></td>
            <td><c:out value="${user.country}"/></td>
            <td><c:out value="${user.city}"/></td>
            <td><c:out value="${user.county}"/></td>
            <td><c:out value="${user.streetName}"/></td>
            <td><c:out value="${user.streetNumber}"/></td>
            <td><c:out value="${user.flatNumber}"/></td>
            <td><c:out value="${user.buildingNumber}"/></td>
            <td>
                <div>
                    <button
                            onClick="location.href = 'http://localhost:8080/Rzv_war/users/update/${user.email}'">
                        Update
                    </button>
                    <button
                            onClick="location.href = 'http://localhost:8080/Rzv_war/users/delete/${user.email}'">
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
            onclick="location.href = 'http://localhost:8080/Rzv_war/users/add-admin'">Add an admin account
    </button>
    <button onclick="location.href = '${pageContext.request.contextPath}'">Back</button>
</div>
</body>

</html>
