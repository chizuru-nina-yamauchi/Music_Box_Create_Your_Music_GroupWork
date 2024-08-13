# MusicBox Application

## Overview

MusicBox is a Spring Boot-based application designed to manage users and roles with a PostgreSQL database. The application utilizes Spring Security for authentication and authorization. Below is a summary of the project's key components.

## Project Structure

### 1. **Entities**
- **User**
    - Represents a user in the system.
    - Fields: `id`, `username`, `password`, `email`, `enabled`, `roles`
    - Optional fields: `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired` (for more granular account status management)
    - Relationships:
        - Many-to-Many relationship with `Role` entity.

- **Role**
    - Represents a role in the system.
    - Fields: `id`, `name`
    - Relationships:
        - Many-to-Many relationship with `User` entity.

### 2. **Repositories**
- **UserRepository**
    - Interface for managing `User` entities.
    - Custom queries for finding users by `username`, `email`, `usernameOrEmail`, and `id`.
    - Methods for saving, finding, and deleting users.

### 3. **Security Configuration**
- **CustomUserDetailsService**
    - Implements `UserDetailsService`.
    - Loads user-specific data (username, password, roles) from the database for authentication.

- **SecurityConfig**
    - Extends `WebSecurityConfigurerAdapter`.
    - Configures:
        - Password encoding using `BCryptPasswordEncoder`.
        - Custom authentication provider using `DaoAuthenticationProvider`.
        - HTTP security settings to define which endpoints require authentication.
        - Form-based login with a custom login page.

## Dependencies

Ensure the following dependencies are included in your `pom.xml` (for Maven) or `build.gradle` (for Gradle):

- **Spring Boot Starter Security**: Provides security features like authentication and authorization.
- **Spring Boot Starter Data JPA**: Simplifies database access using JPA.
- **PostgreSQL Driver**: Allows connection to a PostgreSQL database.
- **Spring Boot DevTools (Optional)**: Provides additional development tools.

## Database Configuration

Configure your `application.properties` or `application.yml` to connect to your PostgreSQL database:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/musicbox
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
#   M u s i c B o x 