<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 07.01.2017
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Product</title>
</head>
<body>
<div id="container" style="margin:auto;width:1000px;border:3px solid lightpink">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body" style="overflow: hidden">
        <div class="inner-body" style="padding:20px;overflow: hidden">
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">

                Name: ${productCommand.name} <br/>
                Description: ${productCommand.description} <br/>
                Manufacturer: ${productCommand.manufacturer} <br/>
                Added by user: ${productCommand.createdByUserId} <br/>
                Price: ${productCommand.price} <br/>
                <c:if test="${productCommand.hasImage}">
                    Image: <img src="/product/image/${productCommand.id}"/>
                </c:if>
                <form action="/cart/add?productId=${productCommand.id}">
                    <input type="submit" value="Add to cart"/>
                </form>
            </div>
        </div>
    </div>

    <div class="footer" style="text-align: center;background: black;color:white;font-family:'Helvetica CE 35 Thin';">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
