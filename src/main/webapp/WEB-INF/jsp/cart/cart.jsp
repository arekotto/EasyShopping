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
            <h3>
                My cart
            </h3>
            <div>
                <table class="table table-striped table-hover">
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
                            <input type="submit" class="btn btn-default" value="Details" />
                        </form></td>
                        <td>
                            <form action="/cart/remove">
                                <input type="hidden" class="btn btn-default" name="productId" value="${productCommand.id}"/>
                                <input type="submit" class="btn btn-default" value="Remove From Cart"/>
                            </form></td>
                    </tr>
                    </c:forEach>
                </table>
                <c:if test="${!empty productCommandList}">
                    <form action="/purchase-invoice/create">
                        <input class="btn btn-default" type="submit" value="Buy">
                    </form>
                </c:if>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>
