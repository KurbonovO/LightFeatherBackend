### Prerequisites
./gradlew clean build

### Run the project
./gradlew bootRun

### Swagger link

http://localhost:8080/swagger-ui/index.html

### docker
#### Run this to build: docker build --tag=spring-template:latest .
#### Run a container: docker run -d --name spring-template -p8080:8080 spring-template:latest
#### When you are done testing, stop the server and remove the container: docker rm -f spring-template