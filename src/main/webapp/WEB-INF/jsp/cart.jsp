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
    <div class="torso">
        <div class="inner-torso">
            <div class="title">
                My cart
            </div>
            <div style="background: white;padding:12px;">

                <table class="cart" style="width:100%">
                    <tr>
                        <th>Item name</th>
                        <th>Description</th>
                        <th>Manufacturer</th>
                        <th>Price</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="productCommand" items="${productCommandList}">
                    <tr>
                        <td>${productCommand.name}</td>
                        <td>${productCommand.description}</td>
                        <td>${productCommand.manufacturer}</td>
                        <td>${productCommand.price}</td>
                        <td><form action="/product/view/${productCommand.id}">
                            <input type="submit" value="Details" />
                        </form></td>
                        <td>
                            <form action="/cart/remove">
                                <input type="hidden" name="productId" value="${productCommand.id}"/>
                                <input style="color: red;" type="submit" value="Remove From Cart"/>
                            </form></td>
                    </tr>
                    </c:forEach>
                </table>
                <form action="/purchase-invoice/create">
                    <input type="submit" name="checkout">
                </form>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
