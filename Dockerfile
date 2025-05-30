FROM gradle:7.6-jdk17-alpine AS build
WORKDIR /app

COPY . .

RUN ./gradlew :api-rest:bootJar

FROM openjdk:17-alpine
WORKDIR /app

COPY --from=build /app/presentation/entry-points/api-rest/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]