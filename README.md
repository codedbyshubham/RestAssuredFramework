  # RestAssured API Automation Framework

  A scalable and modular **API Test Automation Framework** built with **Java, RestAssured, TestNG, Maven, and Log4j2**.  
  It supports **data-driven testing**, generates detailed **Extent HTML reports**, and follows best practices for reusability and maintainability.

  ---

  ##  Tech Stack
  - Java  
  - Maven  
  - RestAssured  
  - TestNG  
  - Apache POI (Excel support)  
  - Extent Reports  
  - Log4j2  


---

##  How to Run Tests
1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/RestAssuredFramework.git
   cd RestAssuredFramework
   ```

2. Run with Maven:
   ```bash
   mvn clean test
   ```

3. Or run with TestNG suite:
   ```bash
   mvn clean test -DsuiteXmlFile=testng.xml
   ```

---

##  Reports & Logs
- **Extent Report:** `reports/ExtentReport.html`  
- **TestNG Report:** `test-output/index.html`  
- **Logs:** `logs/automation.log`  

---

##  Key Features
- Reusable endpoint layer  
- POJO-based payload handling  
- Data-driven testing via Excel  
- Centralized logging with Log4j2  
- Extent Reports + TestNG reports  
- Retry mechanism for flaky tests  
- Scalable, modular design  

---

##  Example Workflow
1. Create User (POST)  
2. Get User (GET)  
3. Update User (PUT)  
4. Delete User (DELETE)  
5. Repeat with multiple test data inputs  


