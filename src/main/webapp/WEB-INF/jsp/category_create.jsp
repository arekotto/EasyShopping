<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ibm
  Date: 2017-01-18
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new category</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
            <div class="title">
                Add category
            </div>
        <form:form style="background:white;padding:12px;"
                action="${request.contextPath}/category/create" commandName="categoryCreationCommand">
            <table class="ncart">
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
                        <form:label path="description">Description:</form:label>

                    </td>
                    <td>
                        <form:textarea path="description" rows="5"/>
                    </td>
                </tr>
            </table>
            <input type="submit" class="formbutton" value="Create">
        </form:form>
    </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
