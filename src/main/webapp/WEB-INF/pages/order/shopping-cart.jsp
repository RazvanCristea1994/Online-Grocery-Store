<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<div>
    <nav>
        <ol>
            <li><a href="${pageContext.request.contextPath}/products/homepage/1"><b>Home</b></a>
            </li>
            <li>Shopping Cart</li>
        </ol>
    </nav>
</div>
<div>
    <h3>Shopping Cart</h3>
</div>
<div>
    <table>
        <thead>
        <tr>
            <td></td>
            <td>PRODUCT</td>
            <td>QUANTITY</td>
            <td>PRICE/UNIT</td>
            <td>PRICE</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${data.orderLines}" var="orderLine" varStatus="counter">
            <tr>
                <td>
                    <div>
                        <img src="${pageContext.request.contextPath}/resources/ui-theme/_ui/images/image-product-${counter.count}.jpg"/>
                    </div>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/products/${orderLine.productId}">
                        <c:out value="${orderLine.productName}"/>
                    </a>
                </td>
                <td>
                    <c:out value="${orderLine.quantity}"/>
                </td>
                <td>
                    <c:set var="formatedProductPrice">
                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                          value="${orderLine.productPrice}"/>
                    </c:set>
                    <c:out value="${formatedProductPrice}"/>$
                </td>
                <td>
                    <c:set var="formatedTotalPrice">
                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                          value="${orderLine.totalPrice}"/>
                    </c:set>
                    <c:out value="${formatedTotalPrice}"/>$
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <div>
            <div>Subtotal</div>
        </div>
        <div>
            <div>Delivery costs</div>
        </div>
        <div>
            <div>Number of Products</div>
            <div>${data.numberOfProducts}</div>
        </div>
        <div>
            <div>${status}</div>
        </div>
        <hr/>
        <div>
            <div>Total</div>
            <div>
                <c:set var="formatedTotalPrice">
                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                      value="${data.totalPrice}"/>
                </c:set>
                <c:out value="${formatedTotalPrice}"/>$
            </div>
        </div>
        <form:form method="POST" action="/internship_war_exploded/orders">
            <button type="submit">CHECKOUT</button>
        </form:form>
</div>
</body>
</html>
