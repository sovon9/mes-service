# use base jdk image
FROM eclipse-temurin:17-jdk
# set the working dir
WORKDIR /app

ARG VERSION
# take jar file location
ARG JAR_FILE

COPY $JAR_FILE app.jar

LABEL version=$VERSION

ENTRYPOINT["java", "-jar", "app.jar"]