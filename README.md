# digital-cards

Digital cards is a management platform that helps university professors keep their business cards updated. This repository is for the frontend part of the project, built with React and Next.js.

## Backend

### Description

This project is a backend application developed with [Spring Boot](https://spring.io/projects/spring-boot). It uses MySQL 8.0 as the database.

### Requirements

- Java 17
- Maven 3.6.3 or higher
- MySQL 8.0

### Environment Setup

#### Database

1. Install MySQL 8.0.
2. Create a database:

   ```sql
   CREATE DATABASE career_test;

   ```

3. Configure the application.properties or application.yml file in src/main/resources/ with the database credentials.

   ```java
   spring.datasource.url= ${DB_URL}
   spring.datasource.username=${USRNM}
   spring.datasource.password=${PSWORD}
   spring.jpa.show-sql=true
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   ```

#### .env

    DB_URL=jdbc:mysql://localhost:3306/career_test;
    PSWORD=;
    SECRET=zdtlD3JK56m6wTTgsNFhqzjqP;
    USRNM=root

#### Configuration for profile image

To ensure the profile image is handled correctly:

1. After running the Spring application once and the database is created, execute the following SQL command:

   ```sql
   ALTER TABLE profile MODIFY COLUMN photo LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
   ```

## Frontend

### Requirements Dev

###### Node 20.8/npm

### Getting Started

#### Note

Make sure to have the backend project running, which is located in the root of the main project.

#### Local

Step 1 - install

```bash
npm i
```

Step 2 - run

```bash
npm run dev
```

Step 3 - Web

```bash
http://localhost:3000/
```

Step 4 - Add Variable Global in .env.local

```bash
NEXT_PUBLIC_SERVER_URL={insert_url}
```
