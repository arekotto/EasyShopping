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
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div class="title">
                Change Password
            </div>
            <div style="background:white; ">
                <c:if test="${not empty userChangePasswordCommand.errorMessage}">
                    <br/>
                    <h3 style="color:red; text-align:center;">${userChangePasswordCommand.errorMessage}</h3>
                    <br/>
                </c:if>
                <form:form cssStyle="width:50%;margin:0px auto;" action="${request.contextPath}/user/save-password"
                           commandName="userChangePasswordCommand">
                    <table class="ncart">
                        <tr>
                            <td>
                                <form:label path="currentPassword">Current Password:</form:label>

                            </td>
                            <td>
                                <form:input path="currentPassword" type="password"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="newPassword">New Password:</form:label>
                            </td>
                            <td>
                                <form:input path="newPassword" type="password"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="newPasswordRetyped">Retype New Password:</form:label>

                            </td>
                            <td>
                                <form:input path="newPasswordRetyped" type="password"/>
                            </td>
                        </tr>

                    </table>
                    <input style="margin-bottom: 10px;" type="submit" class="formbutton" value="Save">
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

