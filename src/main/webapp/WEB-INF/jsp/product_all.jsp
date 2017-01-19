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
    <div class="body" style="overflow: hidden;">
        <div class="inner-body" style="padding:20px;">

            <h3 class="text-bold text-info">
                Product List
            </h3>
            <table>
                <tbody>
                <tr>
                    <td>Name</td>
                    <td>Description</td>
                    <td>Manufacturer</td>
                    <td>Category</td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach var="productCommand" items="${productCommandList}">

                <tr>
                    <td>${productCommand.name}<td/>
                    <td>${productCommand.description}<td/>
                    <td>${productCommand.manufacturer}<td/>
                    <td>${productCommand.category}<td/>
                    <td>
                        <a href="/product/view/${productCommand.id}">
                            Details:
                        </a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${isOnlyForUser}">
                                <a href="/product/remove?productId=${productCommand.id}" style="color:red">Remove</a>
                                <a href="/product/edit?productId=${productCommand.id}" style="color:blue">Edit</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/cart/add?productId=${productCommand.id}">Add to cart</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
    <%--<div class="footer" style="text-align: center;background: black;color:white;font-family:'Helvetica CE 35 Thin';">--%>
        <%--<jsp:include page="${request.contextPath}/footer"></jsp:include>--%>
    <%--</div>--%>

</div>
</body>
</html>
