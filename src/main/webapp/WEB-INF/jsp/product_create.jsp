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
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <div class="inner-body">
        <form:form cssStyle="width:50%;margin:0px auto;" action="${request.contextPath}/product/create"
                   commandName="productCreationCommand">
                <table>
                    <tr>
                        <td>
                            <form:label path="name">Product Name:</form:label>

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
                            <form:input path="description" rows="5"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="manufacturer">Manufacturer:</form:label>
                        </td>
                        <td>
                            <form:input path="manufacturer"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="category">Product Category:</form:label>
                        </td>
                        <td>
                            <form:input path="category"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="price">Price:</form:label>
                        </td>
                        <td>
                            <form:input type="number" step="0.01" path="price"/>
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
