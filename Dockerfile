FROM tomcat:8.5

RUN rm -rf /usr/local/tomcat/webapps/ROOT

RUN mkdir "/usr/local/tomcat/keystore"

COPY ssl_keystore /usr/local/tomcat/keystore/

COPY server.xml "/usr/local/tomcat/conf/server.xml"

COPY /build/libs/EasyShopping.war /usr/local/tomcat/webapps/ROOT.war

COPY ROOT.xml /usr/local/tomcat/conf/Catalina/localhost/

RUN startup.sh

EXPOSE 8443