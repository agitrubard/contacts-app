# Contacts Application

<img src="/documents/icon.png?raw=true" alt="Icon" width="50"/> 

This application consists of two microservices, Contact and Report, forming a simple phone directory system. These
services communicate asynchronously to carry out the following processes:

### **Contact Service**

- Create new contacts
- Delete contacts
- Add or remove communication details (such as phone, email, location) for a contact
- List existing contacts and retrieve their details

### **Report Service**

- Accept requests for location-based statistical reports
- Generate reports asynchronously (queued in the background without creating bottlenecks)
- Track each report’s status (e.g., In Progress, Completed)
- Provide endpoints to list reports and retrieve report details

### **Scenario and Features**

- **Contacts:** The system maintains a unique UUID, first and last name, company name, and any associated communication
  details (phone number, email address, location) for each contact.
- **Reports:** Reports are generated based on location information, covering statistics such as how many contacts and
  phone numbers are registered in that location. When a user initiates a report request, the report is processed
  asynchronously, and its status (Preparing, Completed) can be checked later.

With these two microservices, the system separates responsibilities for contact/communication information management (
the Contact service) and for generating location-based statistical reports (the Report service).

---

## Tech Stack

**Language**

* Java 17

**Framework**

* Spring Boot 3.4.x
    * Spring Boot Web
    * Spring Data JPA
    * Spring Boot Actuator
    * Spring Boot Test (Junit)
    * Spring Boot AMQP

**Build Tool**

* Maven

**Database**

* PostgreSQL

**Database Migration Tool**

* Liquibase

**3rd Party Dependencies**

* Lombok
* Mapstruct
* Easy Random

**Message Broker**

* RabbitMQ

**Software Development Process**

* TDD

**Version Control**

* Git
* GitHub

**APIs Interaction Platform**

* Postman

--- 

---

# Getting Started

---

## Building The Project With Tests

```
./mvnw clean install
```

## Building The Project Without Tests

```
./mvnw clean install -DskipTests
```

## Running Dependencies as Containers

Before running the project, you must execute the following command to start the PostgreSQL and RabbitMQ containers for
both microservices:

```
docker compose up -d --build
```

If you want to recreate PostgreSQL and RabbitMQ containers, you can run the following command:

```
docker compose up --force-recreate -d --build
```

If you want to stop PostgreSQL and RabbitMQ containers, you can run the following command:

```
docker compose down -v
```

---

## Postman

### [API Documentation](https://documenter.getpostman.com/view/23090035/2sAYX3rNwy) & [Workspace](https://www.postman.com/agitrubard/workspace/contacts-application)

---

# Project Infrastructure

## 🏛️ HexaLayered Architecture

![](/documents/architecture/hexalayered-architecture.png?raw=true)

> **Reference: [HexaLayered Architecture](https://github.com/agitrubard/hexalayered-architecture)**
