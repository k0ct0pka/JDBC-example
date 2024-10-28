<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 07.10.2024
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <form action="${pageContext.request.contextPath}/authenticate" method="post">
        <label >
            Code:
            <input type="text" name="code">
        </label>
        <br>
        <input type="submit" value="submit">
    </form>
</head>
<body>

</body>
</html>
