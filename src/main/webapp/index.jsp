<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<button type="button"
        onclick="window.location.href = 'http://localhost:8080/Rzv_war/products'">Products
</button>
<button type="button"
        onclick="window.location.href = 'http://localhost:8080/Rzv_war/categories'">Categories
</button>
<sec:authorize access="hasAuthority('ADMIN')">
    <button type="button" onclick="window.location.href = 'http://localhost:8080/Rzv_war/admin'">Users</button>
    <button type="button" onclick="window.location.href = 'http://localhost:8080/Rzv_war/products/get-all-reviews'">Get reviews</button>
</sec:authorize>
<hr>

<button type="button"
        onclick="window.location.href = 'http://localhost:8080/Rzv_war/user/create-customer-account'">Sign Up
</button>

<sec:authorize access="isAnonymous()">
    <button type="button" onclick="window.location.href = 'http://localhost:8080/Rzv_war/user/login'">Log in</button>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <button type="button" onclick="window.location.href = 'http://localhost:8080/Rzv_war/logout'">Log out</button>
    <button type="button" onclick="window.location.href = 'http://localhost:8080/Rzv_war/user/update-my-account/'">My Account</button>
</sec:authorize>
    </body>
</html>
