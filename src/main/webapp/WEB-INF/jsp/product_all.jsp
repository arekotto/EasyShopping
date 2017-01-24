<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>All Products</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body" style="overflow: hidden;">
        <div class="inner-body" style="padding:20px;">
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <h3 class="text-bold text-info">
                    <c:choose>
                        <c:when test="${isOnlyForUser}">
                            My Products
                        </c:when>
                        <c:otherwise>
                            Product List
                        </c:otherwise>
                    </c:choose>
                </h3>
            </div>
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <c:if test="${!isOnlyForUser}">

                    <p>
                        <form:form cssStyle="width:50%;margin:0px auto;"
                                   action="${request.contextPath}/product/search"
                                   commandName="searchCommand">

                            <form:label path="searchedPhrase">Search:</form:label>
                            <form:input path="searchedPhrase"/>

                            <input type="submit" class="formbutton" value="Search">

                        </form:form>
                    </p>
                </c:if>
                <table>
                    <c:forEach var="productCommand" items="${productCommandList}">

                        <tr>
                            <c:if test="${productCommand.hasImage}">
                                <td>
                                    <img src="/product/image/${productCommand.id}"/>
                                <td/>
                            </c:if>

                            <td>
                                    ${productCommand.name}
                            </td>
                            <td>
                                    ${productCommand.manufacturer}
                            </td>
                            <td>
                                    ${productCommand.description}
                            </td>
                            <td>
                                    ${productCommand.category}
                            </td>
                            <td>
                                <form action="/product/view/${productCommand.id}">
                                    <input type="submit" value="Details"/>
                                </form>

                            </td>

                            <c:choose>
                                <c:when test="${isOnlyForUser}">
                                    <td>
                                        <form action="/product/remove">
                                            <input type="hidden" name="productId" value="${productCommand.id}"/>
                                            <input type="submit" value="Remove" style="color:red;"/>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="/product/edit">
                                            <input type="hidden" name="productId" value="${productCommand.id}"/>
                                            <input type="submit" value="Edit" style="color:darkgoldenrod"/>
                                        </form>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <form action="/cart/add">
                                            <input type="hidden" name="productId" value="${productCommand.id}"/>
                                            <input type="submit" value="Add to cart"/>
                                        </form>
                                    </td>
                                    <%--<a href="/cart/add?productId=${productCommand.id}">Add to cart</a>--%>
                                </c:otherwise>
                            </c:choose>
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
