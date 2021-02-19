<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update Account</title>
</head>
<body>
<h1>Update Account</h1>
<hr>

<form:form method="PUT" action="http://localhost:8080/Rzv_war/user/update-my-account/" modelAttribute="customer">
    <table>
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
            <td><label><form:label path="streetName">Street Name</form:label></label></td>
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
            <td><input type="submit" value="Update"/></td>
            <td><label>${status}</label></td>
        </tr>
    </table>

</form:form>
<button onclick="location.href = 'http://localhost:8080/Rzv_war/user/delete'">Delete my Account</button>
<button type="button" name="back" onclick="location.href = '${pageContext.request.contextPath}'">Back</button>
</html>
