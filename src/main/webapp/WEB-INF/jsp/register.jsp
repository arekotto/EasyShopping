<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <form:form action="${request.contextPath}/user/create"
                   commandName="userRegistrationCommand">
            <fieldset>
                <p>
                    <form:label path="name">Name:</form:label>
                    <form:input path="name"/>
                    <br>
                </p>

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
