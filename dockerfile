FROM ubuntu AS builder

RUN apt-get update && \
    apt-get install -y git maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app/http_file_server
COPY ./* ./
RUN mvn clean package

FROM openjdk:jre-alpine
WORKDIR /app
COPY --from=builder /app/http_file_server/target/solid_project-1.0-SNAPSHOT-jar-with-dependencies.jar ./
CMD java -jar solid_project-1.0-SNAPSHOT-jar-with-dependencies.jar
