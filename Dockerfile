FROM tomcat:latest

COPY /build/libs/EasyShopping.war /usr/local/tomcat/webapps/

RUN startup.sh

EXPOSE 8080