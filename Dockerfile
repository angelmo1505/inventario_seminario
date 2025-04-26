FROM amazoncorretto:17
ARG JAR_FILE=./build/libs/seminario-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} seminario.jar
EXPOSE 8085
ENTRYPOINT [ "java","-jar","/seminario.jar","--server.address=0.0.0.0" ] 