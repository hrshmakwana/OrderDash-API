# OrderDash API 🍕

This is a complete, enterprise-grade back-end RESTful API for a food delivery service, similar to Zomato or Swiggy. This project is built with Java and the Spring Boot framework, following modern API design and security best practices.

---

## Features Implemented
- [x] **User Authentication & Authorization:** Secure user registration and login with JWT (JSON Web Token) based authentication.
- [x] **Restaurant Management:** Full CRUD (Create, Read, Update, Delete) and searching capabilities, including filtering restaurants by cuisine.
- [x] **Ordering System:** Authenticated users can create orders linked to their account and view their complete order history.
- [x] **Review & Rating System:** Authenticated users can post reviews and ratings for specific restaurants.

---

## Tech Stack
* **Java 17**
* **Spring Boot 3**
* **Spring Security** (for JWT Authentication)
* **Spring Data JPA** (with Hibernate)
* **PostgreSQL**
* **Maven**

---

## Project Architecture
This API follows a classic **3-Layer Architecture** to ensure a clean separation of concerns, making the application scalable and maintainable.

* **Controller Layer:** Handles all incoming HTTP requests, validates input, and delegates business logic to the service layer.
* **Service Layer:** Contains the core business logic of the application. It orchestrates operations between different models and repositories.
* **Repository Layer:** Responsible for all database interactions using Spring Data JPA, abstracting the data source.



---

## Getting Started

### Prerequisites
* Java 17 or later
* Maven 3.6 or later
* PostgreSQL running locally

### Configuration
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/hrshmakwana/OrderDash-API.git](https://github.com/hrshmakwana/OrderDash-API.git)
    ```
2.  **Create the PostgreSQL Database:**
    ```sql
    CREATE DATABASE orderdash_db;
    ```
3.  **Configure the Application:**
    Open `src/main/resources/application.properties` and update the `spring.datasource.username` and `spring.datasource.password` with your PostgreSQL credentials.

### Running the Application
You can run the application from your IDE by running the `main` method in `OrderDashApplication.java`, or from the command line:
```bash
mvn spring-boot:run
```
The application will start on `http://localhost:8080`.

---

## API Endpoints Documentation
Use a tool like Postman to test the endpoints.
*Note: The request bodies below are examples. Please replace the values with your own data.*

### Authentication

| Method | Endpoint             | Auth?  | Description           | Request Body Example                            | Successful Response (200 OK)                                     |
| :----- | :------------------- | :----- | :-------------------- | :---------------------------------------------- | :--------------------------------------------------------------- |
| `POST` | `/api/auth/register` | Public | Registers a new user. | `{"name": "Test", "email": "test@test.com", "password": "123"}` | `{ "id": 1, "name": "Test", "email": "test@test.com" }`            |
| `POST` | `/api/auth/login`    | Public | Logs in a user.       | `{"email": "test@test.com", "password": "123"}` | `{ "jwtToken": "eyJhbGciOiJIUzI1NiJ9..." }`                      |

### Restaurants

| Method | Endpoint                          | Auth?  | Description                   | Request Body Example                                     |
| :----- | :-------------------------------- | :----- | :---------------------------- | :------------------------------------------------------- |
| `GET`  | `/api/v1/restaurants`             | Public | Get all restaurants.          | (None)                                                   |
| `GET`  | `/api/v1/restaurants?cuisine=Italian` | Public | Filter restaurants by cuisine.    | (None)                                                   |
| `GET`  | `/api/v1/restaurants/{id}`        | Public | Get a single restaurant by ID. | (None)                                                   |
| `POST` | `/api/v1/restaurants`             | Bearer | Creates a new restaurant.     | `{"name": "Pizza Place", "address": "123 Main", "cuisine": "Italian"}` |

### Orders

| Method | Endpoint                   | Auth?  | Description                  | Request Body Example                         |
| :----- | :------------------------- | :----- | :--------------------------- | :------------------------------------------- |
| `POST` | `/api/v1/orders`           | Bearer | Creates a new order.         | `{"restaurantId": 1, "totalAmount": 25.50}`  |
| `GET`  | `/api/v1/orders/my-orders` | Bearer | Get the current user's orders. | (None)                                       |

### Reviews

| Method | Endpoint                           | Auth?  | Description                  | Request Body Example                     |
| :----- | :--------------------------------- | :----- | :--------------------------- | :--------------------------------------- |
| `POST` | `/api/v1/restaurants/{id}/reviews` | Bearer | Add a review to a restaurant. | `{"rating": 5, "comment": "Great food!"}` |

---

## Future Enhancements
This project provides a solid foundation. Future improvements could include:

- **Pagination:** For endpoints that return lists (like `GET /api/v1/restaurants`) to handle large amounts of data efficiently.
- **User Roles:** Differentiating between `USER` and `ADMIN` roles, where admins have special privileges (like adding new restaurants).
- **Automated Testing:** Implementing a suite of unit and integration tests to ensure code quality and reliability.
- **Deployment:** Containerizing the application with Docker and deploying it to a cloud platform like AWS or Heroku.
