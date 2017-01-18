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
<div id="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">

        <form:form action="${request.contextPath}/category/create"
                   commandName="categoryCreationCommand">
            <fieldset>

                <p>
                    <form:label path="name">Category Name:</form:label>
                    <form:input path="name"/>
                    <br>
                </p>
                <p>
                    <form:label path="description">Description:</form:label>
                    <form:input path="description"/>
                    <br>
                </p>
                <p>
                    <input type="submit" class="formbutton" value="Create">

                    <br>
                </p>

            </fieldset>
        </form:form>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
