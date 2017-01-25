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
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div style="background:white; ">
                <table class="ncart">
                    <tr>
                        <td>
                            Name:
                        </td>
                        <td>
                            ${productCommand.name}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Description:
                        </td>
                        <td>
                            ${productCommand.description}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Category:
                        </td>
                        <td>
                            ${productCommand.categoryName}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Manufacturer:
                        </td>
                        <td>
                            ${productCommand.manufacturer}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Added by user:
                        </td>
                        <td>
                            ${productCommand.createdByUserId}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Price:
                        </td>
                        <td>
                            ${productCommand.price}
                        </td>
                    </tr>
                </table>
                <c:if test="${productCommand.hasImage}">
                    <img style="max-width:700px;margin-top:12px;margin-bottom: 12px;" src="/product/image/${productCommand.id}"/>
                </c:if>
                <c:if test="${isUserProduct != null && !isUserProduct}">
                <form action="/cart/add">
                    <input type="hidden" name="productId" value="${productCommand.id}"/>
                    <input type="submit" value="Add to cart"/>
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
