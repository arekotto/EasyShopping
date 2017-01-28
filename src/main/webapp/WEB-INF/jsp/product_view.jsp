<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <div>
                <div style="float:left">
                    <table class="cart" style="background:white;">
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
                        <img style="max-width:700px;margin-top:12px;margin-bottom: 12px;"
                             src="/product/image/${productCommand.id}"/>
                    </c:if>
                    <c:if test="${isUserProduct != null && !isUserProduct}">
                        <form action="/cart/add">
                            <input type="hidden" name="productId" value="${productCommand.id}"/>
                            <input type="submit" value="Add to cart"/>
                        </form>
                    </c:if>
                </div>
                <c:if test="${!isReviewed} || ${isUserProduct}">
                    <div style="float:right; background: white">
                        <div class="news-header">
                            Write review
                        </div>
                        <div class="news-body">

                            <form:form cssStyle="width:50%;margin:10px auto;"
                                       action="/product/review"
                                       commandName="reviewCommand">
                                <form:select path="rating" style="float:left">
                                    <form:options items="${ratings}"/>
                                </form:select>
                                <form:textarea path="reviewText" rows="5"></form:textarea>
                                <input type="hidden" name="reviewCommand" value="${reviewCommand}">
                                <input type="hidden" name="productId" value="${productCommand.id}">
                                <input type="submit" class="formButton" value="Send Review">
                            </form:form>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div class="title"> Reviews:</div>
            <div class="cart">
                <c:if test="${hasReviews}">
                    <c:forEach var="review" items="${productCommand.reviews}">
                        <div class="item">
                            <tr>
                                <td> Rated by:</td>
                                <td> ${review.userName} <br></td>
                                <td> ${review.rating} <br></td>
                                <td> ${review.reviewText} <br></td>
                            </tr>
                        </div>
                    </c:forEach>
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
