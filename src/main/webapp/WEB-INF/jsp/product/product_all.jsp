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
    <div class="torso">
        <div class="inner-torso">
            <div class="row">
                <c:choose>
                    <c:when test="${isOnlyForUser}">
                        <h3>
                            My Products
                        </h3>
                    </c:when>
                    <c:otherwise>
                        <h3>
                            Product List
                        </h3>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="row">
                <div class="input-group col-md-3" role="group">
                    <c:if test="${!isOnlyForUser}">
                        <form:form action="${request.contextPath}/EasyShopping/product/search" commandName="searchCommand">
                            <form:select path="searchCategory" class="form-control" style="height:auto;">
                                <form:options items="${categoryCommandList}" itemLabel="name" itemValue="id"/>
                            </form:select>
                            <form:input path="searchedPhrase" class="form-control"/>
                            <span class="input-group-btn">
                                <input type="submit" class="formbutton btn btn-default" value="Search">
                            </span>
                        </form:form>
                    </c:if>
                </div>
                <div class="col-md-9">
                    <c:forEach var="productCommand" items="${productCommandList}">
                        <div class="well">
                            <div class="row">
                                <div class="col-md-4">
                                    <c:if test="${productCommand.hasImage}">
                                        <img style="max-width:150px; max-height: 150px;"
                                             class="img-thumbnail"
                                             src="/EasyShopping/product/image/${productCommand.id}"/>
                                    </c:if>
                                </div>
                                <div class="col-md-8">
                                    <p>Name: ${productCommand.name}</p>
                                    <p>Description: ${productCommand.description}</p>
                                    <p>Manufacturer: ${productCommand.manufacturer}</p>
                                    <p>Category: ${productCommand.categoryName}</p>
                                    <p>Rating: ${productCommand.averageRating}</p>

                                    <c:if test="${!isOnlyForUser}">
                                        <form action="/EasyShopping/product/view/${productCommand.id}">
                                            <input class="btn btn-default" type="submit" value="Details"/>
                                        </form>
                                    </c:if>

                                    <c:choose>
                                        <c:when test="${isOnlyForUser}">
                                        <span>
                                            <form action="/EasyShopping/product/remove">
                                                <input type="hidden" name="productId"
                                                       value="${productCommand.id}"/>
                                                <input class="btn btn-default" type="submit" value="Remove"/>
                                            </form>
                                           </span>
                                            <span>
                                             <form action="/EasyShopping/product/edit">
                                                 <input type="hidden" name="productId" value="${productCommand.id}"/>
                                                 <input class="btn btn-default" type="submit" value="Edit"/>
                                              </form>
                                           </span>
                                        </c:when>
                                        <c:when test="${!productCommand.shouldHideAddToCartButton}">
                                            <span>
                                               <form action="/EasyShopping/cart/add">
                                                   <input class="btn btn-default" type="hidden" name="productId"
                                                          value="${productCommand.id}"/>
                                                   <input class="btn btn-default" type="submit"
                                                          value="Add to cart"/>
                                             </form>
                                           </span>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
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
