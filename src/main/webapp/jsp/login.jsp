<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 07.10.2024
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label>
        Name:
        <input type="text" name="name">
    </label>
    <br>
    <label>
        Email:
        <input type="text" name="email">
    </label>
    <br>
    <label>
        Password:
        <input type="password" name="password">
    </label>
    <br>
    <input type="submit" value="submit">
</form>
</body>
</html>
