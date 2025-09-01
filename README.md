# Petshop

A simple Spring Boot REST API for managing pets in a shop. It demonstrates a clean-layered architecture with JPA entities, MapStruct mappers, validation, and a friendly error model. OpenAPI/Swagger UI is included for exploring and trying endpoints.

## Database Structure

![ERD Diagram](docs/ERD.png)

## What it does
- Manage pets (create, read, update, delete)
- Validate request payloads with clear error messages
- Store data in an in-memory HSQLDB database initialized from SQL scripts (schema.sql, data.sql)

## Tech stack
- Java 21
- Spring Boot 3 (Web, Validation, Data JPA, Actuator)
- HSQLDB (in-memory)
- MapStruct (DTO mapping)
- springdoc-openapi (Swagger UI)

## Quick start
Prerequisites: Java 21 installed. No external database is required.

1) Run the application
- Using Gradle (recommended during development):
  ```bash
  ./gradlew bootRun
  ```
- Or build a jar and run it:
  ```bash
  ./gradlew clean build
  java -jar build/libs/petshop-0.0.1-SNAPSHOT.jar
  ```

2) Open Swagger UI
- http://localhost:8080/swagger-ui/index.html

3) Sample health check (if actuator is enabled by your profile):
- http://localhost:8080/actuator/health

## Available Endpoints
Base URL: http://localhost:8080

- POST /pet
  - Description: Add a pet
  - Body (application/json): PetDto
  - Responses: 200 OK, 400 PetType not found

- GET /pet/{petId}
  - Description: Get a pet by ID
  - Responses: 200 OK (PetDto), 404 Not Found

- GET /pets
  - Description: List all pets
  - Responses: 200 OK (array of PetInfo)

- PUT /pet/{petId}
  - Description: Update a pet (null fields are ignored)
  - Body (application/json): PetDto
  - Responses: 200 OK, 400 Validation error, 404 Not Found / PetType not found

- DELETE /pet/{petId}
  - Description: Delete a pet by ID (also deletes related sale entries, if any)
  - Responses: 200 OK, 404 Not Found

For interactive docs and request/response schemas, use Swagger UI.

## Project Structure
- src/main/java
  - eu.urmas.petshop
    - PetshopApplication.java (Spring Boot entry point)
    - controller/pet (REST controller, DTOs)
    - service/pet (business logic)
    - persistence (JPA entities, repositories, mappers)
    - infrastructure (DB helpers, REST errors, exception handling)
- src/main/resources
  - application.properties (in-memory HSQLDB config)
  - schema.sql, data.sql (DB init scripts)
- docs/ERD.png (entity relationship diagram)

## Non-standard configuration / Notes
- In-memory HSQLDB with SQL init:
  - Spring is configured to use an in-memory HSQLDB (jdbc:hsqldb:mem:mydb).
  - Hibernate DDL auto is disabled and schema/data are loaded via Spring SQL initializer (see application.properties, schema.sql, data.sql).
- Optional HSQLDB TCP server for IDE access:
  - Activate with Spring profile `hsql-server` to expose the in-memory DB over TCP on port 9001.
  - Connection details (also commented in application.properties):
    - User: `sa`
    - URL: `jdbc:hsqldb:hsql://localhost:9001/mydb`
    - Password: empty
- Ports:
  - API: 8080
  - HSQLDB server (for IDE connection): 9001 (only when profile `hsql-server` is active)

## Running tests
```bash
./gradlew test
```

## Building
```bash
./gradlew clean build
```
The build produces a runnable jar under `build/libs/`.
