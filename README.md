# robot-apocalypse

---------------------------------------
Robot Apocalypse Management Service
---------------------------------------
Technologies/Tools:
-------------------
IDE: IntelliJ (STS/Eclipse)
Java: 18
Build tool: Maven
Spring Boot: 2.7.1
Database: H2

Command to build the Project:
-----------------------------
mvn clean compile package

Command to run the Jar:
------------------------
java -jar robot-apocalypse-0.0.1-SNAPSHOT.jar

Robot Apocalypse Service Swagger for API documentation
-------------------------------------------------------
http://localhost:8080/


Docker commands to build and run the Project
--------------------------------------------------
docker build -t="robot-apocalypse" .

docker run -p 8080:8080 -it --rm robot-apocalypse 

http://localhost:8080/

