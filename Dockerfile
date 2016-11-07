FROM tomcat:8.0.21-jre8
ENV CATALINA_OPTS="-javaagent:/usr/local/tomcat/lib/sqreen-agent-develop-SNAPSHOT-jar-with-dependencies.jar"
ADD ./*.war /usr/local/tomcat/webapps/
ADD ./sqreen-agent/target/sqreen-agent-develop-SNAPSHOT-jar-with-dependencies.jar /usr/local/tomcat/lib/

