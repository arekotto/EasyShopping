<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <h3>
                Add news
            </h3>
            <form:form action="${request.contextPath}/EasyShopping/admin/createnews"
                       commandName="newsCommand"
                       method="post"
                       enctype="multipart/form-data">
                <table class="table table-striped table-hover">
                    <tr>
                        <td>
                            <form:label path="title">Title:</form:label>
                        </td>
                        <td>
                            <form:input path="title"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="content">Content:</form:label>
                        </td>
                        <td>
                            <form:textarea path="content" rows="5"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="author">Author:</form:label>
                        </td>
                        <td>
                            <form:input path="author"/>
                        </td>
                    </tr>
                </table>
                <input type="submit" class="formbutton btn btn-default" value="Create">
            </form:form>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>
