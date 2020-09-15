<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your products</title>
</head>
<body>
<table border= "2">
    <tr>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var = "product" items = "${products}">
        <tr>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/shopping-cart/products/add?id=${product.id}">Buy</a>
            <td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/shopping-cart/products?id=${user.id}">Go to cart</a>
</body>
</html>
