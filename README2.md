# MusicBox Application

## Overview

MusicBox is a Spring Boot-based application designed to manage users, roles, and premium subscriptions using a PostgreSQL database. The application utilizes Spring Security for authentication and authorization. Below is a summary of the project's key components.

## Project Structure

### 1. **Entities**
- **User**
    - Represents a user in the system.
    - Fields: `id`, `username`, `password`, `email`, `enabled`, `roles`
    - Optional fields: `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired` (for more granular account status management)
    - Relationships:
        - Many-to-Many relationship with `Role` entity.
        - One-to-Many relationship with `Subscription` entity.

- **Role**
    - Represents a role in the system.
    - Fields: `id`, `name`
    - Relationships:
        - Many-to-Many relationship with `User` entity.

- **Subscription**
    - Represents a premium subscription in the system.
    - Fields: `id`, `startDate`, `endDate`, `user`
    - Relationships:
        - Many-to-One relationship with `User` entity.

### 2. **Repositories**
- **UserRepository**
    - Interface for managing `User` entities.
    - Custom queries for finding users by `username`, `email`, `usernameOrEmail`, and `id`.
    - Methods for saving, finding, and deleting users.

- **RoleRepository**
    - Interface for managing `Role` entities.
    - Custom queries for finding roles by name.

- **SubscriptionRepository**
    - Interface for managing `Subscription` entities.
    - Custom queries for finding subscriptions by `userId`.

### 3. **Services**
- **UserService**
    - Contains business logic for user management, including registration, login, and profile updates.

- **RoleService**
    - Manages roles, including assigning and retrieving roles.

- **SubscriptionService**
    - Handles the creation, management, and validation of premium subscriptions.

## Subscriber (Premium User) Features

Premium users on our platform have access to the following enhanced features:

1. **Access to Advanced Learning Modules**: Unlock more in-depth music production lessons, tutorials, and interactive exercises that go beyond the basics.

2. **Expanded Sound Libraries**: Gain access to a wider range of instruments, sounds, and samples to use in your music projects.

3. **Project Export Options**: Export your music projects in higher-quality formats or directly to music platforms like Spotify.

4. **Collaborative Features**: Collaborate with others in real-time, sharing projects and working together on compositions.

5. **Additional Storage**: Benefit from more storage space for saving projects and sound samples.

6. **Personalized Feedback and Mentorship**: Receive personalized feedback from mentors or experts within the platform.

7. **Early Access to New Features**: Get early access to new tools or features before they are available to regular users.

## Project Setup
```xml
MusicBoxCreateYourMusicGroupWorkApplication
|
|-- config
|   |-- WebConfig
|
|-- controller
|   |-- HomeController
|
|-- service
|   |-- HomeService
|   |-- RoleService
|   |-- SubscriptionService
|   |-- UserService
|
|-- repository
|   |-- AudioFileRepository
|   |-- RoleRepository
|   |-- SubscriptionRepository
|   |-- UserRepository
|
|-- model
|   |-- Instrument
|   |-- Role
|   |-- Subscription
|   |-- User
|
|-- resources
|   |-- static
|   |-- templates
|       |-- home.html
|
|-- application.properties
```
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
```

