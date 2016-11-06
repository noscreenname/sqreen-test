FROM tomcat:8.0.21-jre8
ADD ./any-random-app.war /usr/local/tomcat/webapps/
ADD ./sqreen-agent/target/*.jar /usr/local/tomcat/lib/
ADD ./sqreen-agent/context-sqreen.xml /usr/local/tomcat/conf/Catalina/localhost/context.xml.default
