<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <button type="button" onclick="window.location.href = 'http://localhost:8080/Rzv_war/user/update-my-account/'">My Account</button>
</sec:authorize>
    </body>
</html>
