FROM openjdk:11 AS builder

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app/http_file_server
COPY . .
RUN mvn clean package

FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/http_file_server/target/*-jar-with-dependencies.jar /app/runner.jar
EXPOSE 8082
ENTRYPOINT java -jar /app/runner.jar
