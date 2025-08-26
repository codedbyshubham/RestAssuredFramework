  # Rest Assured API Automation Framework

  This repository contains a **Rest Assured + TestNG + Maven** framework for automating
  API testing. The sample tests are written against the [Swagger Petstore API](https://petstore.swagger.io/v2).

---

  ## Project Structure
  - src/test/java  
      - base/ → Base classes for setup and configuration  
      - tests/ → API test classes  
      - utils/ → Utility classes (e.g., JSON helpers, property readers)  

  - src/test/resources  
      - testng.xml → TestNG suite configuration  
      - log4j2.xml → Logging configuration  

  - pom.xml → Maven dependencies & plugins

    ---

  ## Tech Stack
  - **Java** (Test implementation)
  - **Rest Assured** (API testing)
  - **TestNG** (Test execution)
  - **Extent Reports** (HTML Reporting)
  - **Apache POI** (Excel support)
  - **Maven** (Build tool & dependency management)
  - **Log4j2** (Logging)

---

  ## Base URL
  The framework uses the following base URL for API requests:
      https://petstore.swagger.io/v2


  ## Running Tests

  Run all tests:
      mvn test

  Run a specific test class:
      mvn -Dtest=UserAPITests test

  Run with TestNG suite:
      mvn clean test -DsuiteXmlFile=testng.xml


  ## Reports
  - TestNG reports are generated under:
      target/surefire-reports/
  - Logs are available in:
      logs/

    ---

    ##  Data-Driven Testing

  Test data is sourced from:
  /TestData/PetStoreTestData.xlsx
  Use the `@DataProvider` annotation in TestNG tests to pull data dynamically.

---


  ##  Key Features

  - CRUD API automation (Create, Read, Update, Delete)
  - Data-driven testing using Excel
  - POJO-based payload management
  - Centralized reusable API endpoints
  - Retry mechanism for flaky tests
  - Automatic logging with Log4j2
  - Extent Report for beautiful test results
    
---

##  Contribution

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---


##  License

This project is licensed under the MIT License - see the LICENSE file for details.

---


##  Acknowledgments

- Swagger PetStore (https://petstore.swagger.io/) for providing a great testing playground.
- RestAssured (https://rest-assured.io/) for API automation.
- ExtentReports (https://extentreports.com/) for elegant reporting.
- Apache POI (https://poi.apache.org/) for Excel handling.

---
