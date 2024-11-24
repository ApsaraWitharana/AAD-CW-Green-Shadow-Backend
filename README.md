<a href="https://git.io/typing-svg">
  <img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&weight=600&size=50&pause=1000&center=true&vCenter=true&color=00FF00&width=835&height=70&lines=GREEN+SHADOW+BACKEND" alt="green shadow" />
</a>

# Green Shadow Backend Project
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)

## Project Overview

The **Green Shadow Backend** project is a robust and scalable server-side application developed to support the **Green Shadow** system. It provides APIs and services to manage dashboards for **Manager**, **Administrator**, and **Scientist** roles. The system allows for CRUD operations and detailed management of crops, equipment, vehicles, staff, fields, monitoring logs, and related entities.  

This backend uses modern frameworks and tools, ensuring security, scalability, and efficient data handling.

---

## Features
- **Role-Based Access Control**: Secure endpoints for Manager, Administrator, and Scientist roles.
- **CRUD Operations**: Manage entities like crops, equipment, vehicles, staff, and logs.
- **Validation**: Ensures data integrity using Spring Validation.
- **Secure Authentication**: JWT-based authentication for user login and role management.
- **Monitoring and Logging**: Log requests and responses using Logback for detailed monitoring.
- **Efficient Mapping**: Model mapping with ModelMapper for DTO and entity conversions.

---

## Table of Contents
1. [Technologies Used](#technologies-used)
2. [Project Structure](#project-structure)
3. [Setup and Installation](#setup-and-installation)
4. [API Endpoints](#api-endpoints)
5. [API Testing](#api-testing)
6. [Usage](#usage)
7. [Contributing](#contributing)
8. [License](#license)
9. [Contact](#contact)

---

## Technologies Used
The backend is built using the following tools and frameworks:

- **Spring Boot**: Backend framework for microservice development.
- **Spring Data JPA**: For seamless interaction with the MySQL database.
- **Spring Web MVC**: To handle RESTful web services.
- **Spring Validation**: Data validation at the API level.
- **Spring Security**: For securing APIs with JWT.
- **Lombok**: Simplifies boilerplate code with annotations.
- **ModelMapper**: For DTO to entity and entity to DTO conversions.
- **Jackson**: For JSON serialization and deserialization.
- **MySQL**: Database management system.
- **JWT**: JSON Web Tokens for secure authentication.
- **Logback**: For application logging.

---

## Project Structure

The backend follows a modular structure for clarity and maintainability. Key packages include:

- **`config`**:  
  Contains configuration classes such as security filters, JWT configurations, and logging filters. These classes are responsible for setting up security mechanisms (e.g., JWT filters) and application-wide logging functionality.  

- **`controller`**:  
  Houses API endpoints to handle HTTP requests and responses. These endpoints manage user interactions and perform CRUD operations for various entities.  

- **`service`**:  
  Implements the business logic of the application. These classes act as intermediaries between controllers and repositories to ensure clean separation of concerns.  

- **`repository`**:  
  Previously referred to as `dao` (Data Access Objects). This package interacts directly with the database to fetch, save, update, or delete data.  

- **`entity`**:  
  Contains JPA entity classes that map to database tables. Each entity represents a specific table and its attributes correspond to table columns.  

- **`dto`**:  
  Data Transfer Objects used for exchanging data between layers. DTOs ensure that only required data is shared between the frontend and backend.  

- **`exception`**:  
  Includes custom exception classes and handlers to manage errors gracefully across the application, ensuring a better user experience and easier debugging.  

- **`util`**:  
  Utility classes offering common functionalities, such as data format conversions, validation utilities, and helper methods used across the application.  

- **`customobject`**:  
  Custom objects designed for specific features or unique utility purposes that don’t fit into other predefined categories.  

---

## Setup and Installation
### Prerequisites
- Java 17 (OpenJDK 17)
- Maven
- MySQL 8.0.33
- Apache Tomcat 10

### Steps
1. **Clone the repository - Backend**
    ```bash
   git clone https://github.com/ApsaraWitharana/AAD-CW-Green-Shadow-Backend.git
    ```
   **Clone the repository - Frontend**
    ```bash
   git clone https://github.com/ApsaraWitharana/AAD-CW-Green-Shadow-Frontend.git
    ```
2. **Configure the database**
    - Create a MySQL database named `green_shadow_system`.
    - Update the `application.properties` file with your MySQL credentials:
    ```properties
    spring.datasource.url = jdbc:mysql://localhost:3306/green_shadow_system?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false
    spring.datasource.username = root
    spring.datasource.password = password
    spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
    ```

3. **Build the project**
    ```bash
    mvn clean install
    ```

4. **Deploy to Tomcat**
    - Ensure Tomcat is installed and running.
    - Copy the generated WAR file from the `target` directory to the Tomcat `webapps` directory.
    - Restart Tomcat.

### Running the Server
After installation, run the server by starting Tomcat. The API will be available at `http://localhost:8080/`.

---

## API Endpoints
Here are the key API endpoints for the Green Shadow Backend:

- **POST /api/auth/login**: User login (JWT-based authentication).
- **GET /api/crops**: Retrieve all crops.
- **POST /api/crops**: Add a new crop.
- **PUT /api/crops/{id}**: Update crop details.
- **DELETE /api/crops/{id}**: Delete a crop.
- **GET /api/equipment**: Retrieve all equipment.
- **POST /api/equipment**: Add new equipment.
  
 For a full list of available endpoints, please refer to the [API Documentation](https://documenter.getpostman.com/view/35385905/2sAYBUDYDL).

---

## API Testing

### Testing with Postman

You can test the Green Shadow Backend API using Postman. Here’s how you can get started:

1. **Import Postman Collection**
   - Download the Postman collection from [this link](https://github.com/ApsaraWitharana/AAD-CW-Green-Shadow-Backend/blob/main/Postman_Collection.json).
   - Import the collection into Postman for easy access to all API requests.

2. **Test Authentication**
   - To authenticate, send a POST request to `/api/auth/login` with the following body:
     ```json
     {
       "username": "your_username",
       "password": "your_password"
     }
     ```
   - Copy the JWT token from the response and use it for authentication in subsequent requests by including it in the **Authorization** header as `Bearer {JWT_TOKEN}`.

3. **Test CRUD Operations**
   - Use the available endpoints (e.g., `/api/crops`, `/api/equipment`) to test the various CRUD operations for managing resources in the system.

4. **Check Logs**
   - You can view detailed logs for your requests in the backend application using Logback, which will help you debug any issues.

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

<div align="center">

#### This project is licensed under the [MIT License](LICENSE)

#### © 2024 All Right Reserved, Designed By [Sachini Apsara](https://github.com/ApsaraWitharana)

</div>
