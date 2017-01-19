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
<body id="home-page-body" background="/img/background.jpg">
<div id="container" style="margin:auto;width:1000px;border:3px solid lightpink">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <div class="inner-body">
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <h3 class="text-bold text-info">
                    Product List
                </h3>
            </div>
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <table>
                    <c:forEach var="productCommand" items="${productCommandList}">

                        <tr>
                            <td>
                                ${productCommand.name}
                            <td/>
                            <td>
                                ${productCommand.description}
                            <td/>
                            <td>
                                ${productCommand.manufacturer}
                            <td/>
                            <td>
                                ${productCommand.category}
                            <td/>
                            <td>
                                <form action="/product/view/${productCommand.id}">
                                    <input type="submit" value="Details" />
                                </form>
                                <%--<a href="/product/view/${productCommand.id}">--%>
                                    <%--Details:--%>
                                <%--</a>--%>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${isOnlyForUser}">
                                        <form action="/product/remove">
                                            <input type="hidden" name="productId" value="${productCommand.id}" />
                                            <input type="submit" value="Remove"  style="color:red;"/>
                                        </form>
                                        <form action="/product/edit">
                                            <input type="hidden" name="productId" value="${productCommand.id}" />
                                            <input type="submit" value="Edit" style="color:darkgoldenrod"/>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="/cart/add">
                                            <input type="hidden" name="productId" value="${productCommand.id}" />
                                            <input type="submit" value="Add to cart"/>
                                        </form>
                                        <%--<a href="/cart/add?productId=${productCommand.id}">Add to cart</a>--%>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>
    </div>
    <%--<div class="footer" style="text-align: center;background: black;color:white;font-family:'Helvetica CE 35 Thin';">--%>
    <%--<jsp:include page="${request.contextPath}/footer"></jsp:include>--%>
    <%--</div>--%>

</div>
</body>
</html>
