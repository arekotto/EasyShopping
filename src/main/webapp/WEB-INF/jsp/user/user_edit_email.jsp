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
    <title>Change Email</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div class="title">
                Change Email
            </div>
            <div style="background:white; ">
                <c:if test="${not empty userChangeEmailCommand.errorMessage}">
                    <br/>
                    <h3 style="color:red; text-align:center;">${userChangeEmailCommand.errorMessage}</h3>
                    <br/>
                </c:if>
                <form:form cssStyle="width:50%;margin:0px auto;" action="${request.contextPath}/user/save-email"
                           commandName="userChangeEmailCommand">
                    <table class="ncart">
                        <tr>
                            <td>
                                <form:label path="newEmail">New Email:</form:label>

                            </td>
                            <td>
                                <form:input path="newEmail"/>
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

