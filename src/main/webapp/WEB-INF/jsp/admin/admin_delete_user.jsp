<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


            <div>

                <table class="cart" style="width:100%">
                    <tr>
                        <th>User name</th>
                        <th>User e-mail</th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>
                                <form action="/admin/remove">
                                <input type="hidden" name="remove" value="${user.id}"/>
                                <input style="color: red;" type="submit" value="Delete user"/>
                            </form>
                            </td>
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