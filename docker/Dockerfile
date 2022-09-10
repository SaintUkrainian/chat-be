FROM maven:openjdk

WORKDIR /chat-service

COPY . .

EXPOSE 8080

CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=prod"]