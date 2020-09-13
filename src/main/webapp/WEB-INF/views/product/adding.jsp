<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New product</title>
</head>
<body>
<h1>New product:</h1>
<form method="post" action="${pageContext.request.contextPath}/product/add">
    <p>Enter product name : <input type="text" name="name"></p>
    <p>Enter product price : <input type="text" name="price"></p>
    <button type="submit">Save product</button>
</form>
<a href = "${pageContext.request.contextPath}/"> Go Back </a>
</body>
</html>
