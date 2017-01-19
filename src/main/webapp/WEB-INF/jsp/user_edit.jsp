<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: arekotto
  Date: 19/01/2017
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Info</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <div class="inner-body">
            <div style="min-height:30px;"></div>
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <h3 class="text-bold text-info">
                    Edit user info
                </h3>
            </div>
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <form:form cssStyle="width:50%;margin:0px auto;" action="${request.contextPath}/user/save"
                           commandName="userStandardCommand">
                    <table>
                        <tr>
                            <td>
                                <form:label path="name">Name:</form:label>

                            </td>
                            <td>
                                <form:input path="name"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="street">Street:</form:label>
                            </td>
                            <td>
                                <form:input path="street"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="city">City:</form:label>

                            </td>
                            <td>
                                <form:input path="city"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="country">Country:</form:label>
                            </td>
                            <td>
                                <form:input path="country"/>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" class="formbutton" value="Save">
                </form:form>
            </div>

            <div style="min-height:30px;"></div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>

