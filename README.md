# Bank Account Microservice

This project is a Spring Boot microservice for managing bank accounts and their customers.
It exposes RESTful services and GraphQL endpoints to interact with data.
The H2 in-memory database facilitates rapid development and testing.

## H2 Console

**URL**: http://localhost:8081/h2-console

![H2 Console](images/image.png)

## REST API

### GET All Accounts

```
http://localhost:8081/api/bankAccounts
```

![GET All Accounts](images/image-1.png)

### GET Account by ID

```
http://localhost:8081/api/bankAccounts/{id}
```

![GET Account by ID](images/image8.png)

### POST Create Account

```
POST http://localhost:8081/api/bankAccounts
```

![POST Create Account](images/image-4.png)

### PUT Update Account

```
PUT http://localhost:8081/api/bankAccounts/{id}
```

![PUT Update Account](images/image-2.png)

### DELETE Account

```
DELETE http://localhost:8081/api/bankAccounts/{id}
```

![DELETE Account](images/image-3.png)

## Spring Data REST

### GET All Accounts (HAL Format)

```
http://localhost:8081/bankAccounts
```

![Spring Data REST - Accounts](images/image-5.png)

### GET All Customers

```
http://localhost:8081/customers
```

![Spring Data REST - Customers](images/image-6.png)

## GraphQL

**URL**: http://localhost:8081/graphiql

![GraphQL Interface](images/image-7.png)
