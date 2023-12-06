FROM gradle:jdk17 AS buildStage
RUN mkdir /seizuretracker
COPY . /seizuretracker
WORKDIR /seizuretracker
RUN gradle bootJar

FROM amazoncorretto:17-alpine as runStage
RUN mkdir /seizuretracker
COPY --from=buildStage /seizuretracker/build/libs/seizuretracker.jar /seizuretracker/app.jar
WORKDIR /seizuretracker
ENTRYPOINT ["java", "-jar", "app.jar"]