# Maven build container 

FROM maven:3.8.5-openjdk-18 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk

#maintainer 
MAINTAINER srilekhakn27@gmail.com
#expose port 8080
EXPOSE 8080

#default command
CMD java -jar /data/robot-apocalypse-0.0.1-SNAPSHOT.jar

COPY --from=maven_build /tmp/target/robot-apocalypse-0.0.1-SNAPSHOT.jar /data/robot-apocalypse-0.0.1-SNAPSHOT.jar
