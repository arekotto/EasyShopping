<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 2016-12-08
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/css/stylesheet.css" rel="stylesheet">
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
            border-right:1px solid #bbb;
        }

        li:last-child {
            border-right: none;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover:not(.active) {
            background-color: #111;
        }

        .active {
            background-color: #4CAF50;
        }
    </style>
</head>
<body>
<div>
    <div>
        <img src="/img/banner.jpg"></img>
        <ul style="font-family: Helvetica-Condensed-Light-Li;">
            <li><a class="active" href="/home">Home</a></li>
            <li><a href="/product/all">Products</a></li>
            <c:choose>
                <c:when test="${headerCommand.isLoggedIn}">
                    <li><a href="/product/createForm">Add Product</a></li>
                    <li><a href="/product/user">My Products</a></li>
                    <li><a href="/cart/view">Cart</a></li>
                    <li><a href="/purchase-invoice/view-all">Orders</a></li>
                    <li><a href="/user/homepage">${headerCommand.userEmail}</a></li>
                    <li><a href="/user/logout">Log out</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/user/login">Log in</a></li>
                    <li><a href="/user/register">Register</a></li>
                </c:otherwise>
            </c:choose>
<c:choose>
    <c:when test="${headerCommand.isAdmin}">
        <li><a href="/admin/main">Admin panel</a></li>
    </c:when>
</c:choose>

            <%--<li style="float:right"><a href="#about">Admin Panel</a></li>--%>
        </ul>

    </div>
</div>
</body>
</html>
