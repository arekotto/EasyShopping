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
    <div class="torso">
        <div class="inner-torso">
            <div class="news">
                <div class="news-header">
                    Radek Potyka kicked out of University of Silesia.
                </div>
                <div class="news-body">
                    Integer fermentum, enim nec dignissim bibendum, justo arcu finibus nisi, ac sollicitudin ante sapien aliquet diam. Aliquam congue augue a elit faucibus sodales. Nullam hendrerit lacus quis nisl aliquam rhoncus. Aliquam porta vel nibh commodo ultricies. Nullam vel enim risus. Nullam sodales erat justo, eu tempus diam vehicula quis. Donec sed semper nulla. Integer egestas elementum tellus, a gravida lorem porttitor quis. Vestibulum vitae tellus eu lacus ultricies volutpat vel vel ante. In quis volutpat diam. Nullam at nisi at lacus porttitor pretium.
                </div>
                <div class="news-footer">
                    Anna Maria Wesołowska 16.03.2012
                </div>
            </div>
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
