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
<div id="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <c:if test="${userLoginCommand.isLoginFailed}">
                <h3>login has failed!</h3>
        </c:if>

        <form:form action="${request.contextPath}/user/authenticate"
                   commandName="userLoginCommand">
            <fieldset>

                <p>
                    <form:label path="login">Login:</form:label>
                    <form:input path="login"/>
                    <br>
                </p>

                <p>
                    <form:label path="password">Password:</form:label>
                    <form:input path="password"/>
                    <br>
                </p>

                <p>
                    <input type="submit" class="formbutton" value="Log In">

                    <br>
                </p>

            </fieldset>
        </form:form>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
