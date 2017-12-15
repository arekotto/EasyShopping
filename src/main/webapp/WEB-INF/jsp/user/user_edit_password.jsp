<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arekotto
  Date: 19/01/2017
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
    <style>
        .input-group-addon {
            min-width: 150px;
            text-align: left;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div  class="form-horizontal">
                <c:if test="${not empty userChangePasswordCommand.errorMessage}">
                    <br/>
                    <div class="alert alert-danger">${userChangePasswordCommand.errorMessage}</div>
                    <br/>
                </c:if>
                <form:form action="${request.contextPath}/EasyShopping/user/save-password" commandName="userChangePasswordCommand">
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Current Password:</span>
                            <form:input path="currentPassword" class="form-control" type="password"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">New Password:</span>
                            <form:input path="newPassword" class="form-control" type="password"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Retype New Password:</span>
                            <form:input path="newPasswordRetyped" class="form-control" type="password"/>
                        </div>
                    </div>
                    <input type="submit" class="formbutton btn btn-default" value="Save">
                </form:form>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>

