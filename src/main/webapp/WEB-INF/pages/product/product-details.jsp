<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Details Page</title>
</head>
<body>
<c:if test="${error==null}">
    <div></div>
    <div>
        <nav>
            <ol>
                <li><a href="${pageContext.request.contextPath}/products/homepage/1"><b>Home</b></a>
                </li>
                <li aria-current="page">Products</li>
                <li aria-current="page">${product.name}</li>
            </ol>
        </nav>
    </div>
    <div>
        <div>
            <div>
                <div>
                        ${product.name}
                </div>
                <div>Rating</div>
                <ul>
                    <li>Lorem ipsum dolor sit amet, consectetur adipiscing elit</li>
                    <li>Sed sollicitudin scelerisque est</li>
                    <li>Aliquam porttitor in diam at consequat</li>
                    <li>Pellentesque efficitur aliquam dolor</li>
                    <li>Vivamus sed odio quis felis mollis fringilla</li>
                    <li>Aenean magna diam, mollis quis laoreet et, sollicitudin vel leo</li>
                    <li>Donec aliquam, metus vel sagittis eleifend, purus erat faucibus risus,
                        sed maximus nisl sapien id nisi
                    </li>
                </ul>
                <div>
                    <strike><c:set var="formatedTotalPrice">
                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                          value="${product.price}"/>
                    </c:set>
                        $<c:out value="${formatedTotalPrice}"/></strike>&nbsp;<span>
                    <c:set var="formatedTotalPrice">
                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                          value="${(product.price - 1) < 0 ? product.price : (product.price - 1)}"/>
                    </c:set>
                    $<c:out value="${formatedTotalPrice}"/>
                </span>
                </div>
                <div>
                    <div>
                        <div>
                            Quantity: <input type="number" min="1" name="quantity"
                                             value="1"/>
                        </div>
                        <button
                                data-ean="${product.id}">
                            ADD TO CART
                        </button>
                        <div>Add to favourites</div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div>
                <div>Product description</div>
                <p>Donec ${product.description} quis pellentesque purus. Nullam viverra velit
                    ac lorem condimentum, ut
                    faucibus eros congue. In
                    laoreet justo sit amet convallis tincidunt. Aenean imperdiet a sapien sed pellentesque. Donec sit
                    amet
                    porttitor quam, quis tempus tellus. Sed quis vestibulum ex. Quisque facilisis metus a dui sagittis,
                    eget
                    malesuada nunc maximus. Ut accumsan diam at ex suscipit commodo in sit amet ante.

                    Suspendisse potenti. Nunc vel dui auctor, laoreet enim eget, semper diam. Etiam tincidunt diam a ex
                    eleifend
                    rhoncus. Nulla accumsan aliquet odio at tempus. Fusce placerat, nunc ut rutrum vulputate, nisl
                    mauris
                    sagittis nulla, non condimentum nisi nisl in neque. Cras lacinia dui ut nunc porta lobortis. Donec
                    in
                    risus
                    ut dui dignissim cursus. Donec quis viverra erat, in laoreet quam. Donec ac nibh velit. Mauris
                    viverra,
                    dolor eu faucibus consectetur, eros quam lacinia nibh, eu pretium felis nibh nec felis. Duis quis
                    erat
                    ultrices, sagittis arcu id, ornare purus. Integer non elit congue, vehicula nunc eu, condimentum
                    nibh.
                    Cras mattis, neque a varius auctor, leo sapien blandit enim, fringilla sodales risus orci id tortor.
                    Nulla viverra in turpis eget blandit. Sed eu lacinia nisi. In lorem metus, tempor eget euismod nec,
                    feugiat ut justo. Vivamus lobortis bibendum velit non auctor. Pellentesque sollicitudin, velit ut
                    scelerisque ultrices, diam justo accumsan mi, ac hendrerit metus tellus sed urna. Sed viverra
                    fermentum
                    nisl ut porttitor. Nullam sagittis orci tellus.</p>
            </div>
        </div>
        <div>
            <div>
                <div>Legendary product</div>
                <div>Warrior Mode</div>
                <p>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices
                    posuere cubilia
                    Curae; Pellentesque dui libero, tempus in nibh volutpat, dictum imperdiet enim. Aliquam erat
                    volutpat.
                    Morbi quis rutrum mauris. Vestibulum eu pulvinar odio.</p>
                <ul>
                    <li>Donec in libero nec nunc euismod porta</li>
                    <li>Duis et ligula eget tortor gravida viverra ut non augue</li>
                    <li>Nulla ac lorem sit amet magna bibendum vehicula in at odio</li>
                    <li>Quisque eget nisl blandit, mollis justo egestas, ultricies est</li>
                    <li>Suspendisse elementum metus ut orci scelerisque congue</li>
                </ul>
            </div>
        </div>
        </div>
        <div>
            <div>
                <div>Product review</div>
                <form method="post" action="${pageContext.request.contextPath}/products/add-review">
                    Your rating: <input type="number" name="rating" value="${review.rating}" min="1" max="5">
                    <br/>
                    Your review: <input type="text" name="text" value="${review.text}">
                    <input type="ean" hidden name="id" value="${product.id}">
                    <br/>
                    <input type="submit" value="SUBMIT REVIEW">
                </form>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${error!=null && !error.equals(\"\")}">
    ${error}
</c:if>
</body>
</html>
