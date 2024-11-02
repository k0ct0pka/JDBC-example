<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 01.11.2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Cars</title>
    <link rel="stylesheet" href="../css/base.css">
    <link rel="stylesheet" href="../css/homePage.css">
</head>
<body>
<div class="header">
    <div class="link"><a href="${pageContext.request.contextPath}/index">Go Back To Home Page</a></div>
    <div class="add"><a href="${pageContext.request.contextPath}/jsp/addCar.jsp">Add Car</a></div>
</div>
<div class="cars">
    <c:forEach items="${cars}" var="car">
        <div class="car-object">
            <div class="car-image"><img src="data:image/png;base64,${car.image}" alt="Failed to load an image."></div>

            <div class="car-owner">

                <p>Mark: ${car.mark}</p>
                <p>Model: ${car.model}</p>
                <p>Year: ${car.year}</p>
                <p>Price: ${car.price}</p>
                <div class="btns">
                    <form action="${pageContext.request.contextPath}/myCars" method="post">
                        <input type="hidden" value="POST" name="_method">
                        <input type="hidden" value="${car.id}" name="carId">
                        <label for="">
                            Mark: <input type="text" value="${car.mark}" name="mark" required>
                        </label>
                        <br>
                        <label for="">
                            Model: <input type="text" value="${car.model}" name="model" required>
                        </label>
                        <br>
                        <label for="">
                            Year: <input type="number" value="${car.year}" name="year" required>
                        </label>
                        <br>
                        <label for="">
                            Price: <input type="number" value="${car.price}" name="price" required>
                        </label>
                        <br>

                        <input type="submit" value="edit">
                    </form>
                    <form action="${pageContext.request.contextPath}/myCars" method="post">
                        <input type="hidden" value="DELETE" name="_method">
                        <input type="hidden" value="${car.id}" name="carId">
                        <input type="submit" value="delete">
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
