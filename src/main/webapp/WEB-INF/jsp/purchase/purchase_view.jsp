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
        <div class="row">
            <div class="col-md-8">
                <table class="table table-striped table-hover">
                    <tr>
                        <th>Item name</th>
                        <th>Description</th>
                        <th>Manufacturer</th>
                        <th>Price</th>
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
            </div>
            <div class="col-md-4">
                <h5>Issued for:<br>
                </h5>
                <h6>
                    <p>${purchaseInvoiceCommand.userName}</p>
                    <p>${purchaseInvoiceCommand.shipToAddressCountry}</p>
                    <p>${purchaseInvoiceCommand.shipToAddressCity}</p>
                    <p>${purchaseInvoiceCommand.shipToAddressStreet}</p>
                </h6>
            </div>
        </div>
        <div class="row">
            <h3>
                <br>
                Total price ${purchaseInvoiceCommand.price} $
                <br><br>
            </h3>
            <c:when test="${purchaseInvoiceCommand.isCancelled}">
                <h5>
                    Cancelled
                </h5>
            </c:when>
            <c:otherwise>
                <form action="/purchase-invoice/cancel/${purchaseInvoiceCommand.id}">
                    <input type="hidden" name="purchaseInvoiceCommandId"
                           value="${purchaseInvoiceCommand.id}"/>
                    <input class="btn btn-default" type="submit" value="Cancel"/>
                </form>
            </c:otherwise>
        </div>
    </div>
</div>
<div class="footer">
    <jsp:include page="${request.contextPath}/footer"></jsp:include>
</div>
</body>
</html>
