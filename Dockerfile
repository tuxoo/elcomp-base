FROM openjdk:17

WORKDIR /app
ADD /build/libs/elcomp-base-0.0.1-SNAPSHOT.jar elcomp-base.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "elcomp-base.jar"]