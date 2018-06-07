# Book Library app project - Spring boot, Spring security, Spring Data JPA, React with Redux and webpack

## About

Build project structure spring boot initializr: https://start.spring.io/

## Technical Stack

- Java 1.8
- Spring boot maven plugin
- Spring boot 2.0.2
- Postgresql 42.2.2
- H2 - for tests
- Lombok abstraction 1.16.20
- JPA with Postgresql/H2 for explanation
- REST API model validation 
- Spring Boot starter test - junit and integration tests
- npm as a package manager and task runner
- react 16.0.0
- ES6 - Support with Babel
- redux dev tools to help you keep track of the app's state
- routing
- webpack 3.6.0 and webpack-dev-server 2.8.2 as a client-side module builder and module loader

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.github.stodorov.UserInvolvementApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:
mvn spring-boot:run

Test on postman or another API development environment /I test with extention of Chrome - Advanced REST client/:
-------------------
## Register new User:
POST - http://localhost:8080/api/register
{
  "username": "stodorov",
  "password": "pass"
}


## User login:
POST - http://localhost:8080/api/login
{
  "username": "stodorov",
  "password": "pass"
}

## Installation
You should clone the repo and install the dependencies.

$ git clone https://github.com/svetlintodorov/BookLibrary 
$ cd src
$ npm install  

## Development
In the development mode. launch the boilerplate app：

// run the dev server http://localhost:8081

$ npm start  

## Resources:
Used two application.yml files to split configuration of datasource for application.
We have additional test config which used for all tests. 
