<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 2017-02-01
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div class="title">
                Users
            </div>


            <div style="background: white;padding:12px;">

                <table class="cart" style="width:100%">
                    <tr>
                        <th>Item name</th>
                        <th>Description</th>
                        <th>Manufacturer</th>
                        <th>Price</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="users" items="${users}">
                        <tr>
                            <td>${users.name}</td>
                            <td>${users.email}</td>
                            <td>${users.isEmailVerified}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>


        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>