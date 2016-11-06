# sqreen-test

1. Build docker image
{code} 
sudo docker build -t ama/tomcat-sqreen .
{code} 

2. Start docker image
{code} 
sudo docker run -p 8080:8080 ama/tomcat-sqreen
{code} 

3. Access a resource on localhost:8080
{code} 
curl -I http://localhost:8080/any-random-app/
{code} 

You should be able to see "X-Instrumented-By: sqreen".
