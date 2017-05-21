<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ibm
  Date: 2017-01-25
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Checkout</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div class="title">
                Purchase Invoice
            </div>
        </div>
        <div class="news" style="float: right;">
            <h5 class="news-header">Issued for:<br>
            </h5>
            <h6 class="news-body">
                ${purchaseInvoiceCommand.userName}<br>
                ${purchaseInvoiceCommand.shipToAddressCountry}<br>
                ${purchaseInvoiceCommand.shipToAddressCity}<br>
                ${purchaseInvoiceCommand.shipToAddressStreet}<br>
            </h6>
        </div>
        <table class="cart">
            <tr>
                <th>Item name</th>
                <th>Description</th>
                <th>Manufacturer</th>
                <th>Price</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="productCommand" items="${purchaseInvoiceCommand.productList}">
                <tr>
                    <td>${productCommand.name}</td>
                    <td>${productCommand.description}</td>
                    <td>${productCommand.manufacturer}</td>
                    <td>${productCommand.price} $</td>
                </tr>
            </c:forEach>
        </table>
        <div class="news" style="float:right">
            <p clss="news-body">
                <br>
                Total price ${purchaseInvoiceCommand.price} $
                <br><br>
            </p>
        </div>

    </div>
</div>
<div class="footer">
    <jsp:include page="${request.contextPath}/footer"></jsp:include>
</div>

</div>
</body>
</html>
