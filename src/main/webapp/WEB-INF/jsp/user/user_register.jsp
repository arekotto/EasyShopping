<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 2016-12-08
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="row">
        <div class="col-lg-12 text-center">
            <c:if test="${userRegistrationCommand.isLoginUnavailable}">
                <h1>
                    <p class="text-danger">
                        Login name is already taken. Please pick another one.
                    </p>
                </h1>
            </c:if>

            <c:if test="${userRegistrationCommand.isPasswordFormatIncorrect}">
                <h1>
                    <p class="text-danger">Password is too short.
                    </p>
                </h1>
            </c:if>

            <c:if test="${userRegistrationCommand.isEmailIncorrect}">
                <h1>
                    <p class="text-danger">Wrong e-mail address.
                    </p>
                </h1>
            </c:if>
            <form:form action="${request.contextPath}/user/create" commandName="userRegistrationCommand">
            <form class="form-horizontal">
                <div class="form-group row">
                    <div class="col-md-6 col-md-offset-3">
                        <form:input path="email" type="text" class="form-control" placeholder="Email"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-6 col-md-offset-3">
                        <form:input path="password" type="password" class="form-control" placeholder="Password"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-6 col-md-offset-3">
                        <form:input path="name" type="text" class="form-control" placeholder="Name"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-6 col-md-offset-3">
                        <form:input path="street" type="text" class="form-control" placeholder="Street"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-6 col-md-offset-3">
                        <form:input path="city" type="text" class="form-control" placeholder="City"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-6 col-md-offset-3">
                        <form:input path="country" type="text" class="form-control" placeholder="Country"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-2 col-md-offset-5">
                        <input type="submit" class="btn btn-default" value="Create user">
                    </div>
                </div>
                </form:form>
            </form>
        </div>
    </div>
</div>
<div class="footer">
    <jsp:include page="${request.contextPath}/footer"></jsp:include>
</div>
</div>
</body>
</html>
