<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arekotto
  Date: 18/01/2017
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Verification</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div>
                <c:choose>
                    <c:when test="${isEmailVerified}">
                        <div class="alert alert-dismissible alert-success">
                            Your email has been successfully verified.
                        </div>
                    </c:when>
                    <c:when test="${isEmailAlreadyVerified}">
                        <div class="alert alert-info">
                            Your email was already verified
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-danger">
                            Your email could not be verified
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>
