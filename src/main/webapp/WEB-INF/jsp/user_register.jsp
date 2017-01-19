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
    <div class="body">
        <c:if test="${userRegistrationCommand.isLoginUnavailable}">
            <h1>Login is already taken. Please pick another one.</h1>
        </c:if>

        <c:if test="${userRegistrationCommand.isPasswordFormatIncorrect}">
            <h1>Password is too short.</h1>
        </c:if>

        <c:if test="${userRegistrationCommand.isEmailIncorrect}">
            <h1>Wrong e-mail address.</h1>
        </c:if>
        <div class="inner-body">
            <div style="min-height:30px;"></div>
        <form:form cssStyle="width:50%;margin:0px auto;" action="${request.contextPath}/user/create"
                   commandName="userRegistrationCommand">
            <table>
                <tr>
                    <td>
                        <form:label path="email">Email:</form:label>
                    </td>
                    <td>
                        <form:input path="email"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="password">Password:</form:label>
                    </td>
                    <td>
                        <form:input path="password"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="name">Name:</form:label>

                    </td>
                    <td>
                        <form:input path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="street">Street:</form:label>
                    </td>
                    <td>
                        <form:input path="street"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="city">City:</form:label>

                    </td>
                    <td>
                        <form:input path="city"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="country">Country:</form:label>
                    </td>
                    <td>
                        <form:input path="country"/>
                    </td>
                </tr>
            </table>
            <input type="submit" class="formbutton" value="Create user">
            </fieldset>
        </form:form>
            <div style="min-height:30px;"></div>
    </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>
