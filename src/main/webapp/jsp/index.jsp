
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="../css/base.css">
    <link rel="stylesheet" href="../css/homePage.css">
</head>
<body>
<div class="header">
    <div class="header-left"><p>кью кью ${user.name}!</p></div>
    <div class="header-right">
        <div class="search-div">
            <form action="${pageContext.request.contextPath}/index" method="post">
                <input type="search" placeholder="Find Car" class="search-box" name="search">
                <input class="search-btn" type="submit" value="">


            </form>
        </div>

        <div class="link"><a href="${pageContext.request.contextPath}/myCars">My Cars</a></div>

    </div>

</div>

<div class="cars">
<c:forEach items="${cars}" var="car">
    <div class="car-object">
        <div class="car-image"><img src="data:image/png;base64,${car.image}" alt="Failed to load an image."></div>

        <div class="car-owner">
            <p>Mark: ${car.mark}</p>
            <p>Model: ${car.model}</p>
            <p>Year: ${car.year}</p>
            <p>Price: ${car.price}$</p>
            <p class="owner-name">Owner Name: ${car.user.name}</p>
        </div>
    </div>
</c:forEach>
</div>
<div class="footer"></div>
</body>
</html>