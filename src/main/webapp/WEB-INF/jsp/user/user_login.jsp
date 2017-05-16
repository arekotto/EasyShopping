<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Janek
  Date: 26/12/2016
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="row">
        <div class="col-lg-12 text-center">
            <form:form action="${request.contextPath}/user/authenticate" commandName="userLoginCommand">
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
                    <div class="col-md-2 col-md-offset-5">
                        <input type="submit" class="btn btn-default" value="Log In">
                    </div>
                </div>
                </form:form>
                <c:if test="${userLoginCommand.isLoginFailed}">
                    <h3>
                        <p class="text-danger"> Login has failed!</p>
                    </h3>
                </c:if>
            </form>
        </div>
        <div class="footer">
            <jsp:include page="${request.contextPath}/footer"></jsp:include>
        </div>
    </div>
</div>
</body>
</html>
