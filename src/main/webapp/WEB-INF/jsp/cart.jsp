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
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body" style="overflow: hidden">
        <div class="inner-body" style="padding:20px;overflow: hidden">
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <h3 class="text-bold text-info">
                    My Cart
                </h3>
            </div>
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">

                <c:forEach var="productCommand" items="${productCommandList}">
                    Name: ${productCommand.name}<br/>
                    Description: ${productCommand.description}<br/>
                    Manufacturer: ${productCommand.manufacturer}<br/>
                    Price: ${productCommand.price}<br/>
                    <form action="/product/view/${productCommand.id}">
                        <input type="submit" value="Details" />
                    </form>
                    <form action="/cart/remove">
                        <input type="hidden" name="productId" value="${productCommand.id}"/>
                        <input style="color: red;" type="submit" value="Remove From Cart"/>
                    </form>

                    <br/>
                    <br/>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
