<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arekotto
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
    <div class="body">
        <div class="inner-body">
            <div style="min-height:30px;"></div>
        <form:form cssStyle="width:50%;margin:0px auto;" action="${request.contextPath}/user/authenticate"
                   commandName="userLoginCommand">
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
                            <form:input  path="password" type="password"/>
                        </td>
                    </tr>
                </table>
                    <input type="submit" class="formbutton" value="Log In">
        </form:form>
            <c:if test="${userLoginCommand.isLoginFailed}">
                <h3>Login has failed!</h3>
            </c:if>
            <div style="min-height:30px;"></div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>
