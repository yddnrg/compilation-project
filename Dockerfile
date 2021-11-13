# 换成你自己的版本
FROM openjdk:12
WORKDIR /app/
COPY ./* ./
RUN javac Main.java
