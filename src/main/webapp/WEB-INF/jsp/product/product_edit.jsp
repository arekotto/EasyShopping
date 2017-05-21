<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: arekotto
  Date: 19/01/2017
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
    <style>
        .input-group-addon {
            min-width: 150px;
            text-align: left;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div>
        <div>
            <h3 class="title">
                Edit item
            </h3>
            <form:form
                    action="${request.contextPath}/product/save?productId=${productCommand.id}&addedByUserId=${productCommand.createdByUserId}"
                    commandName="productCommand"
                    method="post"
                    enctype="multipart/form-data">
                <form:input path="category" disabled="true" type="hidden"/>
                <div class="form-horizontal">
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Product Name:</span>
                            <form:input path="name" class="form-control" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Product Category:</span>
                            <form:input path="categoryName" class="form-control" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Description:</span>
                            <form:textarea path="description" rows="3" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Manufacturer:</span>
                            <form:input path="manufacturer" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-md-6 col-md-offset-3">
                            <span class="input-group-addon">Price:</span>
                            <form:input type="number" step="0.01" path="price" class="form-control"/>
                        </div>
                    </div>
                </div>
                <p>
                    <label>Upload an Image:</label>
                    <input type="file" name="image" id="fileToUpload" class="btn btn-default">
                    <br>
                </p>
                <input type="submit" class="formbutton btn btn-default" value="Save Changes">
            </form:form>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
