<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arekotto
  Date: 18/01/2017
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your cart</title>
</head>
<body id="home-page-body" background="/img/background.jpg">
<div id="container" style="margin:auto;width:1000px;border:3px solid lightpink">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">

        <div class="inner-body">
    <c:forEach var="productCommand" items="${productCommandList}">
        <a href="/product/view/${productCommand.id}">
            Product:
        </a><br/>
        Name: ${productCommand.name}<br/>
        Description: ${productCommand.description}<br/>
        Manufacturer: ${productCommand.manufacturer}<br/>
        <form action="/cart/remove">
            <input type="hidden" name="productId" value="${productCommand.id}" />
            <input style="color: red;"type="submit" value="Remove" />
        </form>

    </c:forEach>
        </div>
</div>
<div class="footer" style="text-align: center;background: black;color:white;font-family:'Helvetica CE 35 Thin';">
    <jsp:include page="${request.contextPath}/footer"></jsp:include>
</div>

</div>
</body>
</html>
