<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your order details</title>
</head>
<body>
<h1>Order Details</h1>

<table border="1">
    <tr>
        <th>id</th>
        <th>product</th>
        <th>price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/user/orders"> Back to your orders </a>
</body>
</html>
