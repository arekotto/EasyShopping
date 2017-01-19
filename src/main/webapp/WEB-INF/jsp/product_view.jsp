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
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <div class="inner-body">
            name: ${productCommand.name} <br/>
            desc: ${productCommand.description} <br/>
            manufacturer: ${productCommand.manufacturer} <br/>
            createdByUserId: ${productCommand.createdByUserId} <br/>
            <a href="/cart/add?productId=${productCommand.id}" >Add to cart</a><br/><br/>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
