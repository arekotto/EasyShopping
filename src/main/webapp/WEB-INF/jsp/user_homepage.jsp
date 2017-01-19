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
    <title>User</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header">
            <jsp:param name="includePath" value="/header"/>
        </jsp:include>
    </div>
    <div class="body">
        <div class="inner-body">
            <div>
                Email: ${userStandardCommand.email} <br/>
                Name: ${userStandardCommand.name} <br/>
                Country: ${userStandardCommand.country} <br/>
                City: ${userStandardCommand.city} <br/>
                Street: ${userStandardCommand.street} <br/>

                <form action="${request.contextPath}/user/edit">
                    <input type="submit" value="Edit Info" />
                </form>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>