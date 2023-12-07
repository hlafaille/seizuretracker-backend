FROM amazoncorretto:17
RUN mkdir /seizuretracker
COPY seizuretracker.jar /seizuretracker/app.jar
WORKDIR /seizuretracker
ENTRYPOINT ["java", "-jar", "app.jar"]