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
    <link rel="stylesheet" href="../css/registration.css">
    <link rel="stylesheet" href="../css/base-authentications.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label>
        Name:
        <br>
        <input type="text" name="name" required>
    </label>
    <br>
    <br>
    <label>
        Email:
        <br>
        <input type="text" name="email" required>
    </label>
    <br>
    <br>
    <label>
        Password:
        <br>
        <input type="password" name="password" required>
    </label>
    <br>
    <br>

    <input type="submit" value="Sign Up" class="submit">

    <a href="${pageContext.request.contextPath}/login" class="link">Go to LogIn</a>
</form>
</body>
</html>
