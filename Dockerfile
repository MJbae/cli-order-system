FROM openjdk:11 AS builder

ENV PROJECT_NAME homework
ENV HOME /usr/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:11
COPY --from=builder /build/libs/*.jar ${PROJECT_NAME}.jar

EXPOSE 50152
ENTRYPOINT java -jar ${PROJECT_NAME}.jar
