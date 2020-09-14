<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<h1> All Products </h1>
<table border = "1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
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
                <a href = "${pageContext.request.contextPath}/shopping-cart/products?id=${product.id}"> Delete </a>
            </td>
        </tr>
        </c:forEach>
</table>
<br>
<button type = "submit"> Complete order </button>
</form>
<br>
<br>
<a href = "${pageContext.request.contextPath}/main"> Fruit store </a>
</body>
</html>
