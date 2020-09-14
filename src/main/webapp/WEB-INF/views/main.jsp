<%@ page contentType = "text/html;charset=UTF-8" %>
<html>
<head>
    <title>Fruit store</title>
</head>
<body>
<h5>${time}</h5>
<h1>Are you ready for the order?</h1>
<h2> Select button for redirection. </h2>
<a href = "${pageContext.request.contextPath}/user/all"> All Users </a>
<a href = "${pageContext.request.contextPath}/product/all"> All Products </a>
<a href = "${pageContext.request.contextPath}/shopping-cart/products"> Shopping Cart </a>
<a href = "${pageContext.request.contextPath}/orders"> All orders </a>
</body>
</html>
