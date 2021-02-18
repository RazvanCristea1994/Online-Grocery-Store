<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Delete my account</title>
</head>
<body>
<h1>Delete my account</h1>
<hr>

<form:form method="DELETE" action="http://localhost:8080/Rzv_war/user/delete-my-account">

    <h3>Are you sure you want to delete your account?</h3>

    <input type="submit" value="DELETE"/>
</form:form>

<div>
    <button type="button" name="back" onclick="history.back()">Back</button>
</div>
</body>
</html>
