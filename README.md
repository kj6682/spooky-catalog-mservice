# spooky-catalog-mservice

## Catalog
A microservice to browse and store items. 


##build: 
mvn clean compile

##run: 
mvn spring-boot:run
  dev the default profile on port 8080

mvn spring-boot:run -Dspring.profiles.active=prod
  prod is on port 8081

##use: 
http://localhost:8080/message

##visit:
https://spring.io/guides/gs/centralized-configuration/
items

