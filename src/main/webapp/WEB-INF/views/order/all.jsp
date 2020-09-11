<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h2>Orders</h2>
<a href="${pageContext.request.contextPath}/">Fruit store</a>
<c:forEach var="order" items="${orders}">
    <p>id = ${order.id} userId = ${order.userId}</p>
    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
        </tr>
        <c:forEach var="product" items="${order.products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
            </tr>
        </c:forEach>
        <a href="${pageContext.request.contextPath}/orders/delete?orderId=${order.id}">delete</a>
    </table>
</c:forEach>
</body>
</html>
