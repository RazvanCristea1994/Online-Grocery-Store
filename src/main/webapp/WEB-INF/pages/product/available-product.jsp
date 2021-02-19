<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<div>

    <div>
        <h3>Trending items</h3>
        <div>
            <div >
                <c:forEach items="${products}" var="element" varStatus="counter">
                    <c:choose>
                        <c:when test="${counter.count < 5}">
                            <div>
                                <div>
                                    <div>
                                        <div>
                                            <img src="${pageContext.request.contextPath}/resources/ui-theme/_ui/images/image-product-${counter.count}.jpg"/>
                                        </div>
                                        <div >
                                            <div>
                                                <a href="${pageContext.request.contextPath}/products/${element.id}">${element.name}</a>
                                            </div>
                                            <div>Rating</div>
                                            <div><b>${element.price}$</b></div>
                                            <input type="number" min="1"
                                                   value="1"/>
                                        </div>
                                    </div>
                                    <button data-ean="${element.id}">ADD TO CART</button>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>

            <div>
                <c:url value="/products/homepage/${pageNumber-1}" var="prev"/>
                <c:if test="${pageNumber > 1}">
                    <a href="<c:out value="${prev}"/>">
                        <svg width="1em" height="1em" viewBox="0 0 16 16"
                             fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M7.854 4.646a.5.5 0 010 .708L5.207 8l2.647 2.646a.5.5 0 01-.708.708l-3-3a.5.5 0 010-.708l3-3a.5.5 0 01.708 0z"
                                  clip-rule="evenodd"/>
                            <path fill-rule="evenodd" d="M4.5 8a.5.5 0 01.5-.5h6.5a.5.5 0 010 1H5a.5.5 0 01-.5-.5z"
                                  clip-rule="evenodd"/>
                        </svg>
                    </a>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" varStatus="loop">
                    <c:choose>
                        <c:when test="${pageNumber == loop.index}">
                            <span>${loop.index}</span>
                        </c:when>
                    </c:choose>
                </c:forEach>

                <c:url value="/products/homepage/${pageNumber + 1}" var="next"/>
                <c:if test="${pageNumber + 1 <= noOfPages}">
                    <a href="<c:out value="${next}"/>">
                        <svg width="1em" height="1em" viewBox="0 0 16 16"
                             fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M8.146 4.646a.5.5 0 01.708 0l3 3a.5.5 0 010 .708l-3 3a.5.5 0 01-.708-.708L10.793 8 8.146 5.354a.5.5 0 010-.708z"
                                  clip-rule="evenodd"/>
                            <path fill-rule="evenodd" d="M4 8a.5.5 0 01.5-.5H11a.5.5 0 010 1H4.5A.5.5 0 014 8z"
                                  clip-rule="evenodd"/>
                        </svg>
                    </a>
                </c:if>
            </div>
        </div>
    </div>

</div>
</body>
</html>