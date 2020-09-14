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
 <c:forEach var="order" items="${orders}">
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
     </table>
</c:forEach>
</table>
</body>
</html>
