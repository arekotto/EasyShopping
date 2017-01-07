<%--
  Created by IntelliJ IDEA.
  User: arekotto
  Date: 16/12/2016
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page2</title>
</head>
<body id="home-page-body" background="/img/background.jpg">
<div id="container" style="margin:auto;width:1000px;border:3px solid lightpink">
    <div class="header">
        <jsp:include page="${request.contextPath}/header">
            <jsp:param name="includePath" value="/header"/>
        </jsp:include>
    </div>
    <div class="body" style="color:white;background-color:#333;height:300px;opacity: 0.8;padding:15px;">
        <div style="height:100%;width:100%;background:black;opacity: 0.9;-webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;">
            Name: ${userStandardCommand.name} <br/>
            Email: ${userStandardCommand.email} <br/>
            City: ${userStandardCommand.city} <br/>
        </div>
    </div>
    <div class="footer" style="text-align: center;background: black;color:white;font-family:'Helvetica CE 35 Thin';">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>