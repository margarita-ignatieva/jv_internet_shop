<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<h1>
    <b:menuitem>All users</b:menuitem>
</h1>

<body>
<table border= "2">
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var = "user" items = "${users}">
        <tr>
            <td>
                <c:out value = "${user.userId}"/>
            </td>
            <td>
                <c:out value = "${user.name}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
