<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order Confirmation</title>
</head>
<body>
<div>
    <ol>
        <li><a href="${pageContext.request.contextPath}/products/homepage/1"><b>Home</b></a>
        </li>
        <li><a href="${pageContext.request.contextPath}/shopping-cart-items"><b>Shopping Cart</b></a></li>
        <li>Confirmation Page</li>
    </ol>
</div>
<div>
    <h1>Thank for your order!</h1>
    <div>We are processing your order now, here are the details</div>
    <div>
        <div>
            <span>Confirmation will be sent to:</span>
            <span>${order.email}</span>
        </div>
        <div>
            <span>Order number:</span>
            <span>${order.id}</span>
        </div>
        <div>
            <span>Order date:</span>
            <span>${order.orderDate}</span>
        </div>
    </div>

    <h3>Ordered products</h3>
    <div>
        <table>
            <thead>
            <tr>
                <td>PRODUCT NAME</td>
                <td>PRICE/UNIT</td>
                <td>QUANTITY</td>
                <td>TOTAL PRODUCTS PRICE</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${order.orderItems}" var="orderItem">
                <tr>
                    <td><c:out value="${orderItem.productName}"/></td>
                    <td><c:out value="${orderItem.price}"/></td>
                    <td><c:out value="${orderItem.quantity}"/></td>
                    <td>
                        <c:set var="formatedTotalPrice">
                            <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                              value="${orderItem.quantity * orderItem.price}"/>
                        </c:set>
                        <c:out value="${formatedTotalPrice}"/>$
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <h3>Total price</h3>
        </div>
        <div>
            <div>
                <c:set var="formatedTotalPrice">
                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                      value="${order.totalCost}"/>
                </c:set>
                <c:out value="${formatedTotalPrice}"/>$
            </div>
        </div>
        <div>One of our employee will get in touch with you shortly!</div>
    </div>
</div>

</body>
</html>
