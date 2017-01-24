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
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <div class="inner-body">
            <form:form cssStyle="width:50%;margin:0px auto;" action="${request.contextPath}/product/save?productId=${productCommand.id}&addedByUserId=${productCommand.createdByUserId}"
                       commandName="productCommand"
                       method="post"
                       enctype="multipart/form-data">
                <table>

                    <tr>
                        <td>
                            <form:label path="name">Product Name:</form:label>

                        </td>
                        <td>
                            <form:input path="name" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="category">Product Category:</form:label>
                        </td>
                        <td>
                            <form:input path="category" disabled="true"/>
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
                            <form:label path="price">Price:</form:label>
                        </td>
                        <td>
                            <form:input type="number" step="0.01" path="price"/>
                        </td>
                    </tr>

                </table>
                <p>
                    <label>Upload an Image:</label>
                    <input type="file" name="image" id="fileToUpload">
                    <br>
                </p>
                <input type="submit" class="formbutton" value="Save Changes">
            </form:form>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>

</div>
</body>
</html>
