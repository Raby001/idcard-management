FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update && apt install -y \
    openjdk-21-jdk \
    nginx \
    openssh-server \
    git \
    php-cli \
    maven

RUN mkdir /var/run/sshd

RUN echo 'root:root123' | chpasswd

EXPOSE 8080
EXPOSE 8443
EXPOSE 22

CMD service ssh start && nginx -g "daemon off;"