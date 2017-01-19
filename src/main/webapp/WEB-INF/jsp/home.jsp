<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 2016-12-08
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="body">
        <div class="inner-body">
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <h3 class="text-bold text-info">
                    Welcome to Easy Shopping!
                </h3>
            </div>
            <div style="background:#BFFFDD; border-radius:10px; border-color: #111111; padding:10px; margin: 10px; ">
                <h5 class="text-bold text-info">
                    It's a web marketplace allowing you to add and sell your products and buy products
                    from other users.
                </h5>
            </div>
        </div>
    </div>
    <div class="footer">
            <h6 class="text-bold text-info">
                Website still requires some final polishing and adjustments.
            </h6>
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>
