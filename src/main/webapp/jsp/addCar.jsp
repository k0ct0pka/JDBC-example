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
<form action="${pageContext.request.contextPath}/myCars" method="post" enctype="multipart/form-data">
    <input type="hidden" value="POST" name="_method">
    <label>
        Image: <input type="file" name="image" multiple>
    </label>
    <br>
    <label>
        Mark:<input type="text" name="mark" multiple>
    </label>
    <br>
    <label>
        Model:<input type="text" name="model" multiple>
    </label>
    <br>
    <label>
        Year: <input type="number" name="year" multiple>
    </label>
    <br>
    <label>
        Price: <input type="number" name="price" multiple>
    </label>
    <br>
    <input type="submit" value="Add" class="submit">
    <a href="${pageContext.request.contextPath}/myCars" class="link">Go To My Cars</a>
</form>
</body>
</html>
