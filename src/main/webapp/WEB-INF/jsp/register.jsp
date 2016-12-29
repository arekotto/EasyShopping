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
    <title>Title</title>
</head>
<body id="register-page-body">
<div id="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">

        <c:if test="${userRegistrationCommand.isLoginUnavailable}">
            <h1>LOGIN IS ALREADY TAKEN. PICK A DIFFERENT ONE!</h1>
        </c:if>

        <c:if test="${userRegistrationCommand.isPasswordFormatIncorrect}">
            <h1>PASSWORD TOO SHORT</h1>
        </c:if>

        <c:if test="${userRegistrationCommand.isEmailIncorrect}">
            <h1>wrong email address</h1>
        </c:if>

        <form:form action="${request.contextPath}/user/create"
                   commandName="userRegistrationCommand">
            <fieldset>

                <p>
                    <form:label path="login">Login:</form:label>
                    <form:input path="login"/>
                    <br>
                </p>

                <p>
                    <form:label path="name">Name:</form:label>
                    <form:input path="name"/>
                    <br>
                </p>

                <p>
                    <form:label path="password">Password:</form:label>
                    <form:input path="password"/>
                    <br>
                </p>

                <p>
                    <form:label path="email">Email:</form:label>
                    <form:input path="email"/>
                    <br>
                </p>

                <p>
                    <form:label path="street">Street:</form:label>
                    <form:input path="street"/>
                    <br>
                </p>

                <p>
                    <form:label path="city">City:</form:label>
                    <form:input path="city"/>
                    <br>
                </p>

                <p>
                    <form:label path="country">Country:</form:label>
                    <form:input path="country"/>
                    <br>
                </p>

                <p>
                    <input type="submit" class="formbutton" value="Save">

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
