<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arekotto
  Date: 07/01/2017
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ALl Products</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div class="title">
                    <c:choose>
                        <c:when test="${isOnlyForUser}">
                            My Products
                        </c:when>
                        <c:otherwise>
                            Product List
                        </c:otherwise>
                    </c:choose>
            </div>
        <c:forEach var="productCommand" items="${productCommandList}">
            <div class="item">
                <table style="width:250px;">
                    <tr>
                        <td>
                            <c:if test="${productCommand.hasImage}">
                                <img style="max-width:150px;" src="/product/image/${productCommand.id}"/>
                            </c:if>
                        </td>
                        <td style="vertical-align:top; padding:8px;">
                            Name: </br>
                            ${productCommand.name}</br>
                            Description: </br>
                            ${productCommand.description}</br>
                            Manufacturer: </br>
                                ${productCommand.manufacturer}</br>
                            Category: </br>
                                ${productCommand.category}
                        </td>
                    </tr>
                    <tr>
                        <td style="vertical-align:top; padding:8px;" align="center">
                            <form action="/product/view/${productCommand.id}">
                                <input type="submit" value="Details"/>
                            </form>
                        </td>
                        <td style="vertical-align:top; padding:8px;" align="center">
                            <c:choose>
                            <c:when test="${isOnlyForUser}">
                        <span>
                            <form action="/product/remove">
                                <input type="hidden" name="productId" value="${productCommand.id}"/>
                                <input type="submit" value="Remove" style="color:red;"/>
                            </form>
                        </span>
                        <span>
                            <form action="/product/edit">
                                <input type="hidden" name="productId" value="${productCommand.id}"/>
                                <input type="submit" value="Edit" style="color:darkgoldenrod"/>
                            </form>
                        </span>
                        </c:when>
                        <c:otherwise>
                            <span>
                                <form action="/cart/add">
                                    <input type="hidden" name="productId" value="${productCommand.id}"/>
                                    <input type="submit" value="Add to cart"/>
                                </form>
                            </span>
                            <%--<a href="/cart/add?productId=${productCommand.id}">Add to cart</a>--%>
                        </c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>
        </div>
    </div>
    <div class="footer">
    <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
