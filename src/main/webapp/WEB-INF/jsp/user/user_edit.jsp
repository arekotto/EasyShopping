<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Change Info</title>
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
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div class="form-horizontal">
                <form:form action="${request.contextPath}/user/save" commandName="userStandardCommand">
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Name:</span>
                            <form:input path="name" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Street:</span>
                            <form:input path="street" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">City:</span>
                            <form:input path="city" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Country:</span>
                            <form:input path="country" class="form-control"/>
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

