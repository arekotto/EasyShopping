<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arekotto
  Date: 16/12/2016
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <style>
        .input-group-addon {
            min-width:150px;
            text-align:left;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header">
            <jsp:param name="includePath" value="/header"/>
        </jsp:include>
    </div>
    <form class="torso">
        <h1 class="inner-torso">
            <h1>
                User info
            </h1>
            <c:if test="${!userStandardCommand.isEmailVerified}">
                <div class="alert alert-info">
                    A verification email has been sent to your address.
                </div>
            </c:if>

            <form class="form-horizontal">
                <form:form action="${request.contextPath}/user/edit"
                           commandName="userStandardCommand">
                    <%--Email: ${userStandardCommand.email}--%>
                    <%--Name: ${userStandardCommand.name}--%>
                    <%--Country: ${userStandardCommand.country}--%>
                    <%--City: ${userStandardCommand.city}--%>
                    <%--Street: ${userStandardCommand.street} <br/>--%>

                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Name:</span>
                            <form:input path="name" type="text" class="form-control" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Street:</span>
                            <form:input path="street" type="text" class="form-control" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">City:</span>
                            <form:input path="city" type="text" class="form-control" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Country:</span>
                            <form:input path="country" type="text" class="form-control" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="btn-group" role="group" aria-label="...">
                            <a class="btn btn-default" role="button" href="${request.contextPath}/user/edit">Edit
                                Info</a>
                            <a class="btn btn-default" role="button" href="${request.contextPath}/user/edit-password">Change
                                Password</a>
                            <a class="btn btn-default" role="button" href="${request.contextPath}/user/edit-email">Change
                                Email</a>
                        </div>
                    </div>
                </form:form>
            </form>
        </h1>
    </form>
</div>
</div>
<div class="footer">
    <jsp:include page="${request.contextPath}/footer"></jsp:include>
</div>
</div>
</body>
</html>