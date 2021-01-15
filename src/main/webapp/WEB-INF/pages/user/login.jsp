<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test='${param.error != null && !param.error.equals("")}'>
    <div>
        <a href="#">&times;</a>
        <c:out value="${param.error}"/>
    </div>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
    <div>
        <label>E-mail:</label>
        <input type="email" name="email"/>
        <div>
            Please provide an E-mail
        </div>
    </div>

    <div>
        <label>Password:</label>
        <input type="password" name="password"/>
        <div>
            Please provide a Password
        </div>
    </div>

    <input type="submit" value="Login">
</form>
</body>
</html>
