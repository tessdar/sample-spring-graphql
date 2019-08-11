
Let's start by taking a quick look at the tech involved.

## Pre-requisites

Here are the exact versions I'll be using:

- [Java 8 (1.8.0_131)](https://java.com)
- [Spring Boot 2.1.6](https://projects.spring.io/spring-boot)
- [GraphQL-Java Spring Boot Starter 5.10.0](https://www.graphql-java-kickstart.com/spring-boot/)
- [Maven 3.5.0](https://maven.apache.org/)
- [Mybatis Spring Boot Starter 2.1.0](http://www.mybatis.org/mybatis-3/)
- [Project Lombok 1.18.8](https://projectlombok.org/)

## Database

This code is based on the Postgres SQL. 

## Build

Go ahead and install all the dependencies.

    mvn clean install

## Deploy

If you need, change the profiles in resources/application.yml.

    java -jar sample-spring-graphql.jar