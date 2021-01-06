<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create Admin Account</title>
</head>
<body>
<h1>Create Admin Account</h1>
<hr>

<form:form method="POST" action="http://localhost:8080/Rzv_war/users/add-admin" modelAttribute="userData">
    <table>
        <tr>
            <td><label><form:label path="email">Email</form:label></label></td>
            <td><form:input path="email"/><label>${email_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="password">Password</form:label></label></td>
            <td><form:input path="password" type="password"/><label>${password_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="firstName">First Name</form:label></label></td>
            <td><form:input path="firstName"/><label>${firstName_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="lastName">Last Name</form:label></label></td>
            <td><form:input path="lastName"/><label>${lastName_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="phoneNumber">Phone Number</form:label></label></td>
            <td><form:input path="phoneNumber"/><label>${phoneNumber_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="country">Country</form:label></label></td>
            <td><form:input path="country"/><label>${country_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="city">City</form:label></label></td>
            <td><form:input path="city"/><label>${city_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="county">County</form:label></label></td>
            <td><form:input path="county"/><label>${county_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="streetName">Street Name</form:label><label></td>
            <td><form:input path="streetName"/><label>${street_name}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="streetNumber">Street Number</form:label></label></td>
            <td><form:input path="streetNumber"/><label>${streetNumber_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="flatNumber">Flat Number</form:label></label></td>
            <td><form:input path="flatNumber"/><label>${flatNumber_error}</label></td>
        </tr>
        <tr>
            <td><label><form:label path="buildingNumber">Building Number</form:label></label></td>
            <td><form:input path="buildingNumber"/><label>${buildingNumber_error}</label></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
            <td><label>${status}</label></td>
        </tr>
    </table>

</form:form>
<div>
    <button onclick="location.href = '${pageContext.request.contextPath}/users'">
        Back
    </button>
</div>
</body>
</html>
