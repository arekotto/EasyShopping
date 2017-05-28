<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Janek
  Date: 2016-12-08
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link data-require="bootstrap@*" data-semver="4.0.5" rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"/>
    <link data-require="bootstrap-css@*" data-semver="4.0.0-alpha.4" rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"/>
    <script data-require="bootstrap@*" data-semver="4.0.5"
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://bootswatch.com/cosmo/bootstrap.min.css"/>
</head>
<body>
<div>
    <h3></h3>
    <div class="row">
        <div class="col-md-12 text-center">
            <div class="jumbotron">
                <h1>Easy Shopping</h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 text-center">
            <div class="btn-group" role="group" aria-label="...">
                <a class="btn btn-default" role="button" href="/home">Home</a>
                <a class="btn btn-default" role="button" href="/product/all">Products</a>
                <c:choose>
                    <c:when test="${headerCommand.isLoggedIn}">
                        <a class="btn btn-default" role="button" href="/product/createForm">Add Product</a>
                        <a class="btn btn-default" role="button" href="/product/user">My Products</a>
                        <a class="btn btn-default" role="button" href="/purchase-invoice/view-all">Orders</a>
                        <a class="btn btn-default" role="button" href="/user/homepage">${headerCommand.userEmail}</a>
                        <a class="btn btn-default" role="button" href="/user/logout">Log out</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-default" role="button" href="/user/login">Log in</a>
                        <a class="btn btn-default" role="button" href="/user/register">Register</a>
                    </c:otherwise>
                </c:choose>
                <a class="btn btn-default" role="button" href="/cart/view">Cart</a>
                <c:choose>
                    <c:when test="${headerCommand.isAdmin}">
                        <a class="btn btn-default" role="button" href="/admin/main">Admin panel</a>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<h3></h3>
</body>
</html>
