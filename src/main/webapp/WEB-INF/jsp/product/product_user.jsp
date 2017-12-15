<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 18.01.2017
  Time: 22:42
  To change this template use File | Settings | File Templates.

  CZY TEN PLIK JEST W OGÓLE POTRZEBNY????

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Products</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <div class="inner-body">

            <c:forEach var="productCommand" items="${productCommandList}">
                <a href="/product/view/${productCommand.id}">
                    Product:
                </a><br/>
                ${productCommand.name}<br/>
                ${productCommand.description}<br/>
                ${productCommand.manufacturer}<br/>
                <a href="/EasyShopping/product/remove?productId=${productCommand.id}" style="color:red">Remove</a><br/><br/>


            </c:forEach>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>