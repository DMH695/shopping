FROM java:8

COPY *.war /shopping-0.0.1-SNAPSHOT.war
CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/shopping-0.0.1-SNAPSHOT.war"]