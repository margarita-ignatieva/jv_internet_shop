<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h2>Orders</h2>
    <table border="1">
        <tr>
            <th>Order id</th>
            <th>User id</th>
            <th>Order details</th>
            <th>Delete order</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td><c:out value="${order.orderId}"/></td>
            </tr>
            <tr>
                <td><c:out value="${order.userId}"/></td>
            </tr>
            <tr>
                <a href="${pageContext.request.contextPath}/order/details?orderID=${order.id}">details</a>

            </tr>
            <tr>
                <a href="${pageContext.request.contextPath}/orders/delete?orderId=${order.id}">delete</a>
            </tr>
        </c:forEach>

    </table>
<a href = "${pageContext.request.contextPath}/main"> Fruit store </a>
</body>
</html>
