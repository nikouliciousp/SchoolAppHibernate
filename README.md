
# SchoolApp

A robust RESTful API for managing school-related data built using **Java**, **JPA (Hibernate)**, and **Jakarta EE**. This project enables CRUD operations for entities such as Teachers, Students, and Courses, offering seamless database interaction and scalability.

## Features

- **RESTful Endpoints:** Easily interact with entities like Teachers and Students via HTTP methods.
- **Persistence:** Utilizes Hibernate for ORM and MySQL as the database.
- **Transaction Management:** Ensures consistency and rollback capability for database operations.
- **CDI Support:** Uses dependency injection for better modularity and testability.
- **Scalability:** Designed to handle multi-threaded environments with thread-safe JPA interactions.

---

## Table of Contents

1. [Getting Started](#getting-started)
2. [Technologies Used](#technologies-used)
3. [Setup Instructions](#setup-instructions)
4. [API Endpoints](#api-endpoints)
5. [Contributing](#contributing)
6. [License](#license)

---

## Getting Started

### Prerequisites

Make sure you have the following installed on your system:

- **Java 11+**
- **Apache Maven**
- **MySQL**
- **Postman** (for API testing)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/nikouliciousp/SchoolAppHibernate.git
   cd SchoolAppHibernate
   ```

2. Configure the database:
   - Create a MySQL database named `schoolapp`.
   - Update `persistence.xml` in the `src/main/resources/META-INF/` directory with your database credentials:
     ```xml
     <property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/schoolapp?serverTimezone=UTC" />
     <property name="hibernate.connection.username" value="your-username" />
     <property name="hibernate.connection.password" value="your-password" />
     ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the server:
   ```bash
   mvn wildfly:run
   ```

5. Access the API at:
   ```
   http://localhost:8080/api
   ```

---

## Technologies Used

- **Java 17**
- **Jakarta EE**
- **JPA (Hibernate)**
- **MySQL**
- **Apache Maven**
- **WildFly/JBoss Application Server**

---

## API Endpoints

### Teachers
- **GET** `/api/teachers` - Fetch all teachers
- **GET** `/api/teachers/{id}` - Fetch teacher by ID
- **POST** `/api/teachers` - Add a new teacher
- **PUT** `/api/teachers/{id}` - Update a teacher
- **DELETE** `/api/teachers/{id}` - Delete a teacher

### Students (example structure; extend as needed)
- **GET** `/api/students` - Fetch all students
- **GET** `/api/students/{id}` - Fetch student by ID
- **POST** `/api/students` - Add a new student
- **PUT** `/api/students/{id}` - Update a student
- **DELETE** `/api/students/{id}` - Delete a student

For a full list of endpoints, check the [API Documentation](#).

---

## Setup Instructions

### Database Configuration
Ensure the MySQL database is running locally, and the credentials in `persistence.xml` match your setup.

### Testing the API
Use Postman or a similar API client to test the endpoints. Import the collection from the `postman` directory in this repository.

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Open a pull request.
