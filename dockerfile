FROM openjdk:11
WORKDIR /usr/app
COPY build/libs/DiabetesRiskService-0.0.1-SNAPSHOT.jar mediscreen-diabetesRisk-microService.jar
CMD  java -jar mediscreen-diabetesRisk-microService.jar