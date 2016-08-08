# spooky-items-service

## Items
A microservice to browse and store items. 


##build: 
mvn clean compile

##run: 

mvn spring-boot:run -Dspring.profiles.active=local
local is on port 8080 and the configuration now is loaded locally with no need of running the config service

mvn spring-boot:run
dev the default profile on port 8080

mvn spring-boot:run -Dspring.profiles.active=prod
prod is on port 8081

##use: 
http://localhost:8080/message

##visit:
https://spring.io/guides/gs/centralized-configuration/
items

##swagger:
http://localhost:8080/v2/api-docs
http://localhost:8080/swagger-ui.html
###reference:
http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

##mongo
by now this application works on a local mongo instance only
thus du not forget to start it with the usual :

mongod
