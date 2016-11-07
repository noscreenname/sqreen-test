# sqreen-test

This project implement a java agent that instrument tomcat's CoyoteAdapter.service() by adding a new header : "X-Instrumented-By: sqreen".

1. Build docker image

`
docker build -t ama/tomcat-sqreen .
` 

2. Start docker image

`
docker run -p 8080:8080 ama/tomcat-sqreen
`

3. Access a resource on localhost:8080

`
curl -I http://localhost:8080/any-random-app/
`

You should be able to see "X-Instrumented-By: sqreen".
