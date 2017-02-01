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
            <div class="title">
                Email Verification
            </div>
            <div style="background: white;padding:12px;">

                <c:choose>
                    <c:when test="${isEmailVerified}">
                        Your email has been successfully verified.
                    </c:when>
                    <c:when test="${isEmailAlreadyVerified}">
                        No need, your email is already verified
                    </c:when>
                    <c:otherwise>
                        Your email could not be verified
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
