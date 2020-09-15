<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your orders</title>
</head>
<body>
<h2>Your orders</h2>
<a href="${pageContext.request.contextPath}/main"> Fruit store </a>
<table border="1">
    <tr>
        <th>Order id</th>
    </tr>

    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.orderId}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/details?orderId=${order.orderId}">Order details</a>
            </td>
        </tr>
    </c:forEach>

</table>
<a href = "${pageContext.request.contextPath}/main"> Fruit store </a>
</body>
</html>
