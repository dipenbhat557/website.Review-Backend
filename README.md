# Website Review Application - Backend

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Website Review Application is a comprehensive web platform for users to review and share their experiences with various websites and online services. Whether you want to provide feedback on an e-commerce site, a social media platform, or any online service, this application makes it easy to express your thoughts, rate services, and discover new websites.

## Features

- **User Registration and Authentication:** Create an account or log in securely to start reviewing websites.
- **Review Submission:** Share your opinions, ratings, and detailed reviews about websites and online services.
- **Search and Discovery:** Easily find reviews for specific websites and explore new online platforms.
- **Rating System:** Contribute to the community by rating websites based on your experiences.
- **Categories and Subcategories:** Organize reviews by categories and subcategories for better navigation.

## Getting Started

### Prerequisites

Before setting up the Website Review Application, ensure you have the following prerequisites:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL Database](https://dev.mysql.com/downloads/installer/)

### Installation

1. Clone the repository:

   git clone git@github.com:dipenbhat557/website.Review-Backend.git
   Build the project:

mvn clean install

2. Configure the database connection in application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/websitereview?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password

3. Start the application:

mvn spring-boot:run

The application will be accessible at http://localhost:8080.

Usage

1. Open your web browser and go to http://localhost:8080.
2. Sign up for an account or log in if you already have one.
3. Start reviewing websites and sharing your experiences!

API Documentation
For detailed API documentation and endpoints, you can access the Swagger UI by visiting http://localhost:8080/swagger-ui/index.html.

Contributing
Contributions to the Website Review Application are welcome! If you'd like to contribute to this project, please follow these steps:

1.Fork the project.
2.Create a new branch (git checkout -b feature/your-feature-name).
3.Commit your changes (git commit -m 'Add some feature').
4.Push to the branch (git push origin feature/your-feature-name).
5.Open a pull request.
