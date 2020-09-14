<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border= "2">
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var = "product" items = "${products}">
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
            <td>
                <a href = "${pageContext.request.contextPath}/product/add?id=${product.id}"> Buy </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
