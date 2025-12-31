
Bookstore Task — Spring Boot REST API

A RESTful API built with **Java 17** and **Spring Boot** that manages a collection of books.  

## Features

- CRUD operations for books
- Input validation using Bean Validation
- Optimistic locking using JPA `@Version`
- Global exception handling
- HTTP Basic Authentication
- H2 in-memory database
- Postman collection for testing


## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- H2 Database
- Gradle


## API Endpoints

### Get all books

**`GET /books`**

**Response – 200 OK**
```
[
  {
    "id": 1,
    "name": "Clean Code",
    "rating": 5,
    "author": "Robert C. Martin"
  }
]
```

### Create a book

**`POST /book`**

**Request body**
```
{
  "name": "Clean Code",
  "rating": 5,
  "author": "Robert C. Martin"
}
```

**Response – 201 Created**
```
{
  "id": 1,
  "name": "Clean Code",
  "rating": 5,
  "author": "Robert C. Martin",
  "version": 0
}
```

### Update a book (Optimistic Locking)

**`PUT /book/{id}`**

**Request body**
```
{
  "name": "Clean Code (Updated)",
  "rating": 4,
  "author": "Robert C. Martin"
}
```

**Response – 200 OK**
```
{
  "id": 1,
  "name": "Clean Code (Updated)",
  "rating": 4,
  "author": "Robert C. Martin"
}
```

**If the version is stale, the request fails with:**

**Response – 409 Conflict**
```
{
  "code": "CONCURRENT_UPDATE",
  "message": "The resource was updated by another request. Please refresh and try again."
}
```

### Delete a book

**`DELETE /book/{id}`**

**Response – 204 No Content**

### Get books count

**`GET /books/count`**

Returns the number of books currently managed by the application.



## Authentication

All endpoints (except `/h2/**`) are protected using HTTP Basic Authentication.

**Default credentials:**
- **Username:** `admin`
- **Password:** `admin123`

**Using Postman:**
1. Authorization tab → Basic Auth
2. Enter the credentials above

**Requests without authentication return:**
- **401 Unauthorized**



## Error Handling

Global exception handling is implemented using `@RestControllerAdvice`.

**Examples:**
- **404 Not Found** → book does not exist
- **409 Conflict** → optimistic locking failure
- **400 Bad Request** → validation errors
- **500 Internal Server Error** → unexpected failures



## Running the Application

### Prerequisites

- Java 17
- Gradle (wrapper included)

### Start the app

```
./gradlew bootRun
```

The API will be available at: **http://localhost:8080**

### H2 Database Console

Accessible at: **http://localhost:8080/h2**

**Connection details:**
- **JDBC URL:** `jdbc:h2:mem:booksdb`
- **Username:** `sa`
- **Password:** (empty)


## Testing with Postman

A Postman collection is included in the repository.

**Suggested test flow:**
1. Create a book
2. Get all books
3. Update the same book twice with the same version (to test optimistic locking)
4. Verify one update succeeds and the other returns 409
5. Delete the book


## Possible Improvements

- JWT-based authentication
- Pagination and sorting for `/books`
- Swagger / OpenAPI documentation
- Persistent database (PostgreSQL)
