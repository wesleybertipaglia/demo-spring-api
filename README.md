# Demo Spring API

This is a Demo Spring API application that allows you to perform CRUD operations on a Demo entity. 

It's really simple and easy to understand, but it includes some of the best practices and features that you can find in a real-world application, you can se the list of [features](#features) below.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running](#running)
- [Entities](#entities)
- [Contributing](#contributing)
- [License](#license)

## Features

The application includes the following functionalities:

- Create, read, update, and delete a Demo entity.
- Entity Validation
- Exception Handling
- Pagination, sorting, and filtering
- Security
- API documentation (Swagger)
- Success and failure tests for CRUD operations
- Logging
- Production, Development, and Test profiles
- Docker support (Dockerfile, docker-compose)
- CI/CD pipeline

## Getting Started

### Running with Docker

The easiest way to run the application is with Docker.

**1. Prerequisites**

- **Docker**, you can download and install it from [here](https://www.docker.com/products/docker-desktop).
- **Docker Compose**, you can download and install it from [here](https://docs.docker.com/compose/install/).

**2. Create the .env file**

- Create a `.env` file in the root directory following the .env.example file.
- Edit the `.env` file and set the environment variables.

**3. Running**

To run the application, execute the following command:

```bash
docker-compose up
```

> The application will be available at `http://localhost:8080`.

> The API documentation is available at `http://localhost:8080/swagger-ui.html`.

## Entities

The following Entity-Relationship Diagram (ERD) shows the properties of the Demo entity:

```mermaid
classDiagram
    Demo {
        Long id
        String name
        String description
    }
```

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
