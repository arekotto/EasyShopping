<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Arek
  Date: 07.01.2017
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a new product</title>
</head>
<body>
<div id="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">

        <form:form action="${request.contextPath}/product/create"
                   commandName="productCreationCommand">
            <fieldset>

                <p>
                    <form:label path="name">Product Name:</form:label>
                    <form:input path="name"/>
                    <br>
                </p>

                <p>
                    <form:label path="description">Description:</form:label>
                    <form:input path="description"/>
                    <br>
                </p>

                <p>
                    <form:label path="manufacturer">Manufacturer:</form:label>
                    <form:input path="manufacturer"/>
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
