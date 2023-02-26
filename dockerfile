FROM ubuntu

RUN apt-get update && \
    apt-get install -y git maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir /http_server
WORKDIR /http_server

RUN git clone https://github.com/Maxayer/http_file_server.git

WORKDIR /http_server/http_file_server

EXPOSE 8082
CMD mvn clean test
