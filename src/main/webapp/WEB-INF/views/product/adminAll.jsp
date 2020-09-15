<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
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
                <a href="${pageContext.request.contextPath}/products/delete?id=${product.id}">Delete</a>
            <td>
            <td>
                <a href = "${pageContext.request.contextPath}/products/add?id=${product.id}"> Add new product </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
