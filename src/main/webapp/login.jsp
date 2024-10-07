<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 01.10.2024
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login">
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
