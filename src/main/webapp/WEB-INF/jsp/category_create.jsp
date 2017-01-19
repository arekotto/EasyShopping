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
<div id="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
            <h3 class="text-bold text-info">
                Category List
            </h3>
        </div>
        <form:form style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px;"
                action="${request.contextPath}/category/create" commandName="categoryCreationCommand">
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
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
