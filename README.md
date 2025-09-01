# OrderDash API

This is a complete, enterprise-grade back-end RESTful API for a food delivery service, similar to Zomato. This project is built with Java and the Spring Boot framework, following modern API design and security best practices.

## Features Implemented
- [x] User Authentication & Authorization (Register, Login with JWT)
- [x] Restaurant Management (Create, View All, View by ID, Filter by Cuisine)
- [x] Ordering System (Authenticated users can create orders and view their history)
- [x] Review & Rating System (Authenticated users can post reviews)

## Tech Stack
* **Java 17**
* **Spring Boot 3**
* **Spring Security** (for JWT Authentication)
* **Spring Data JPA** (with Hibernate)
* **PostgreSQL**
* **Maven**

## Getting Started

### Prerequisites
* Java 17 or later
* Maven 3.6 or later
* PostgreSQL running locally

### Configuration
1.  **Clone the repository:**
    ```bash
    git clone https://github.com/hrshmakwana/OrderDash-API.git
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

## API Endpoints Documentation

Use a tool like Postman to test the endpoints.

### Authentication

| Method | Endpoint                  | Auth?    | Description             | Request Body Example                                     |
| :----- | :------------------------ | :------- | :---------------------- | :------------------------------------------------------- |
| `POST` | `/api/auth/register`      | Public   | Registers a new user.   | `{"name": "Test", "email": "test@test.com", "password": "123"}` |
| `POST` | `/api/auth/login`         | Public   | Logs in a user.         | `{"email": "test@test.com", "password": "123"}`          |

### Restaurants

| Method | Endpoint                  | Auth?    | Description             | Request Body Example                                     |
| :----- | :------------------------ | :------- | :---------------------- | :------------------------------------------------------- |
| `GET`  | `/api/v1/restaurants`     | Public   | Get all restaurants.    | (None)                                                   |
| `GET`  | `/api/v1/restaurants?cuisine=Italian` | Public | Filter by cuisine. | (None) |
| `GET`  | `/api/v1/restaurants/{id}`| Public   | Get a single restaurant.| (None)                                                   |
| `POST` | `/api/v1/restaurants`     | Bearer   | Creates a new restaurant.| `{"name": "Pizza Place", "address": "123 Main", "cuisine": "Italian"}` |

### Orders

| Method | Endpoint                  | Auth?    | Description             | Request Body Example                                     |
| :----- | :------------------------ | :------- | :---------------------- | :------------------------------------------------------- |
| `POST` | `/api/v1/orders`          | Bearer   | Creates a new order.    | `{"restaurantId": 1, "totalAmount": 25.50}`              |
| `GET`  | `/api/v1/orders/my-orders`| Bearer   | Get current user's orders.| (None)                                                   |

### Reviews

| Method | Endpoint                      | Auth?    | Description                 | Request Body Example                                     |
| :----- | :---------------------------- | :------- | :-------------------------- | :------------------------------------------------------- |
| `POST` | `/api/v1/restaurants/{id}/reviews`| Bearer | Add a review to a restaurant.| `{"rating": 5, "comment": "Great food!"}`                |
