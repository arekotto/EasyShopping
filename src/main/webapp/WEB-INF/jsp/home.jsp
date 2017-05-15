<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Home </title>
</head>
<body>
<div class="container">
    <div class="header">
        <jsp:include page="${request.contextPath}/header"></jsp:include>
    </div>
    <div class="torso">
        <div class="inner-torso">
<c:forEach var="newsCommand" items="${newsCommandList}">
            <div class="news">
                <div class="news-header">
                        ${newsCommand.title}
                </div>
                <div class="news-body">
                        ${newsCommand.content}
                </div>
                <div class="news-footer">
                        ${newsCommand.author}
                </div>
            </div>
</c:forEach>
            <div class="news">
                <div class="news-header">
                    New promotion.
                </div>
                <div class="news-body">
                    You underestimated my power Mr Ferdek.
                </div>
                <div class="news-footer">
                    Grubas One 15.03.2012
                </div>
            </div>
            <div class="news">
                <div class="news-header">
                    Welcome to our online shop!
                </div>
                <div class="news-body">
                    Quisque vitae tellus dignissim, consectetur metus eu, imperdiet magna. Morbi eros augue, volutpat vel tincidunt a, consequat eget risus. Praesent eu diam ligula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas ultricies iaculis accumsan. Aliquam accumsan ex leo, nec accumsan mauris rhoncus id. Donec odio lectus, vehicula vitae nibh ut, vulputate lobortis erat. Ut blandit sagittis velit. Sed pharetra lorem orci, nec auctor dolor ultrices vel. Cras ut vulputate sapien. Praesent porta sollicitudin cursus. Donec ultrices fringilla justo quis porttitor. Fusce nec gravida tortor.
                </div>
                <div class="news-footer">
                    Anna Maria Wesołowska 20.11.1337
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="${request.contextPath}/footer"></jsp:include>
    </div>
</div>
</body>
</html>
