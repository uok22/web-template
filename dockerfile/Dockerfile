FROM openjdk:11

USER root

RUN mkdir -p /ftp
RUN mkdir -p /ftp/anon

COPY . /ftp

EXPOSE 2122
EXPOSE 45445-45455

ENTRYPOINT ["java", "-jar", "/ftp/ftp-test-server.jar"]
