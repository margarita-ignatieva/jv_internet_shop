<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h2>Orders</h2>
<a href = "${pageContext.request.contextPath}/main"> Fruit store </a>
<c:forEach var="order" items="${orders}">
    <p>id = ${order.id} userId = ${order.userId}</p>
    <table border="1">
        <tr>
            <th>name</th>
        </tr>
        <c:forEach var="product" items="${order.products}">
            <tr>
                <td>${product.name}</td>
            </tr>
        </c:forEach>
        <a href="${pageContext.request.contextPath}/orders/delete?orderId=${order.id}">delete</a>
    </table>
</c:forEach>
<form>
    <input type="button" value="Order details"
           onClick='location.href="${pageContext.request.contextPath}/order/details"'>
</form>
</body>
</html>
