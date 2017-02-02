<%--
  Created by IntelliJ IDEA.
  User: Andrzej
  Date: 2017-02-01
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
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
            <div class="title">
                Admin panel
            </div>
            <div style="background:white; width:920px;padding:20px;">
                <a href="/admin/deleteuser">Users</a>
                <a href="/admin/addnews">News</a>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>