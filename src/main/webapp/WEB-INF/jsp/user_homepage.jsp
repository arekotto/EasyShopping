<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <div class="torso">
        <div class="inner-torso">
                <div class="title">
                        User info
                </div>
                <div style="background:white; ">
                    <form:form cssStyle="width:50%;margin:0px auto;" action="${request.contextPath}/user/edit"
                               commandName="userStandardCommand">
                        <%--Email: ${userStandardCommand.email}--%>
                        <%--Name: ${userStandardCommand.name}--%>
                        <%--Country: ${userStandardCommand.country}--%>
                        <%--City: ${userStandardCommand.city}--%>
                        <%--Street: ${userStandardCommand.street} <br/>--%>
                        <table class="ncart">
                            <tr>
                                <td>
                                    <form:label path="name">Name:</form:label>

                                </td>
                                <td>
                                    <form:input path="name" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="street">Street:</form:label>
                                </td>
                                <td>
                                    <form:input path="street" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="city">City:</form:label>

                                </td>
                                <td>
                                    <form:input path="city" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="country">Country:</form:label>
                                </td>
                                <td>
                                    <form:input path="country" disabled="true"/>
                                </td>
                            </tr>
                        </table>
                        <form action="${request.contextPath}/user/edit">
                            <input style="margin-bottom: 10px;" type="submit" value="Edit Info"/>
                        </form>
                    </form:form>
                </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>