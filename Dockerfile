# Une seule étape : le JDK complet, Maven, les sources… et root. Ça marche, mais c'est lourd et risqué.
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN ./mvnw -B -q package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/lanterne-0.1.0.jar"]
