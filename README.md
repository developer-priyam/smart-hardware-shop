# Smart Hardware Shop - Docs

# Java (Spring boot)
  - Product Inventory Service 
  - Shopping Cart Service
  - Service Discovery Service - using Netflix Eureka
  - Door Service - API Gateway
  - User Service
Java 17

# Angular - UI
Angular 13

## Java
### Important Classes
#### Product Inventory Service
InventoryController - It is the front controller of the inventory
#### Shopping cart Service - It is for Cart operations done by user.
CartController
#### User Service - This is a service out of scope for this task. Just provides a defaut / dummy user .
UserController

There are different endpoints in each respective service, Each have the documentation within the service

### POJS's
#### Book - model for book details
#### User - model for user details

### Data store and loader
#### DummyDataLoader - It implements ommandLineRunner and loads dummy data on application start.
#### DummyLibraryDataStore - It has static map type member variable for Book and USer data. This in-memory data is used for all the operations.

### Exception Handling using Custom Exceptions
ControllerAdvice and ExceptionHandler is used to handle and translate custom exceptions to http response
1. DuplicateCopyIssueException
2. IssueLimitReachedException
3. NotEnoughCopyException
4. UserNotFoundException

## Angular
This has following components
### App component
### Lib - dashboard component - loads on route ''.  Holds Book and user component
### Book detail component - displays the book list and performs issue operation
### User Component - show the books issued to a user and performs book return operation and user load request.

# Test Cases
## Java - Junit, Services have both Unit and Integration test for Product Inventory and Shopping cart Service

## Angular - Test cases are written in karma

# How To Build and Run
- Run 'mvn install' in each java project inside backend folder.
- Run 'npm' i inside ui folder
- Order of starting Service
  - Service Discovery
  - API Gateway
  - Product Inventory
  - Shopping Cart
  - User Service
- Finally, Start Angular App - run npm start inside UI folder

## Ports:
Java app start 
  - Door (API Gateway) - at 8080 - http://localhost:8080 // Not Fully Functional
  - Product Inventory - at 8081 - http://localhost:8081
  - Shopping Cart - at 8082 - http://localhost:8082
  - User Service - at 8083 - http://localhost:8083
  - Service DDiscovery (Netflix Eureka) - at 8761 - http://localhost:8761
  
Angular app starts at 4200 - http://localhost:4200

# Run Tests Cases
  - Java
    - mvn test inside eac java service after above build step. (for both integration and unit tests)
    - npm test inside ui folder.


