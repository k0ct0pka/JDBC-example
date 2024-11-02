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
    <link rel="stylesheet" href="../css/base.css">
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/base-authentications.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <br>
    <label>
        Email:
        <br>
        <input type="text" name="email">
    </label>
    <br>
    <br>
    <label>
        Password:
        <br>
        <input type="password" name="password">
    </label>
    <br>

    <br>
    <input type="submit" value="Log In" class="submit">
    <a href="${pageContext.request.contextPath}/registration" class="link">Go To Registration</a>
</form>
</body>
</html>
