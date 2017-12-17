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
    <style>
        .input-group-addon {
            min-width:100px;
            text-align:left;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="row">
        <div class="panel panel-default col-md-6 col-md-offset-1">
            <div class="panel-heading">
                <h2>
                    ${productCommand.name}
                </h2>
            </div>
            <div class="panel-body">
                <p>
                    Description:
                    ${productCommand.description}
                </p>
                <p>
                    Category:
                    ${productCommand.categoryName}
                </p>
                <p>
                    Manufacturer:
                    ${productCommand.manufacturer}
                </p>
                <p>
                    Added by user:
                    ${productCommand.createdByUserId}
                </p>
                <p>
                    Price:
                    ${productCommand.price}
                </p>
                <p>
                    Rating:
                    ${productCommand.averageRating}
                </p>
                <p>
                    Times sold:
                    ${productCommand.quantitySold}
                </p>
                <c:choose>
                    <c:when test="${productCommand.quantity > 0}">
                        <p>
                            Left in stock:
                                ${productCommand.quantity}
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p>
                            <strong>Out of stock!</strong>
                        </p>
                    </c:otherwise>
                </c:choose>
                <c:if test="${productCommand.hasImage}">
                    <img class="img-fluid" src="/product/image/${productCommand.id}"/>
                </c:if>
                <c:if test="${!productCommand.shouldHideAddToCartButton}">
                    <form action="/cart/add">
                        <input type="hidden" name="productId" value="${productCommand.id}"
                               class="btn btn-default"/>
                        <input type="submit" value="Add to cart" class="btn btn-default"/>
                    </form>
                </c:if>
            </div>
        </div>

        <div class="col-md-4">
            <c:if test="${!isReviewed && !isUserProduct}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Write a review
                    </div>
                    <div class="panel-body">
                        <div class="form-horizontal">
                            <form:form action="/product/review"
                                       commandName="reviewCommand">
                                <div class="form-group row">
                                    <div class="input-group col-md-10 col-md-offset-1">
                                        <span class="input-group-addon">Rating:</span>
                                        <form:select path="rating" class="form-control" style="height:auto;">
                                            <form:options items="${ratings}"/>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="input-group col-md-10 col-md-offset-1">
                                        <span class="input-group-addon">Description:</span>
                                        <form:textarea path="reviewText" rows="5" class="form-control"></form:textarea>
                                    </div>
                                </div>
                                <input type="hidden" name="reviewCommand" value="${reviewCommand}"
                                       class="btn btn-default">
                                <input type="hidden" name="productId" value="${productCommand.id}"
                                       class="btn btn-default">
                                <input type="submit" class="formButton btn btn-default" value="Send Review">
                            </form:form>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="torso">
                <div class="panel panel-default">
                    <div class="panel-heading"> Reviews:</div>
                    <div class="panel-body">
                        <c:if test="${hasReviews}">
                            <c:forEach var="review" items="${productCommand.reviews}">
                                <div>
                                    <p>Rated by: ${review.userName}</p>
                                    <p>Rate: ${review.rating}</p>
                                    <p>~ ${review.reviewText}</p>
                                    <br>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>
