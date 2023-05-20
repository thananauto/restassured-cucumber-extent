# restassured-cucumber-extent
This is the sample framework which build on cucumber + TestNG + Extent report to the test the API application using Rest Assured


## Features
* All real time request, response, body, header and cookie can be found as attachment of each scenario of extent 
  report
* Each scenario response body is validated against its schema
* Test data can be read from `YAML` file
* `Owner` library is used to read the properties file
* `Lombok` library is used to generate getter and setter methods
* All scenario can be execute in parallel, since runner class extends `AbstractTestNGCucumberTests`
* Failed scenario can be executed again in same command, the report generation is the combination of `TestRunnerIT.java` and `RerunnerIT.java`

## References
1. [Grasshopper chirp](https://ghchirp.online/4199/)
2. [Owner](https://github.com/matteobaccan/owner)
3. [Lombok](https://projectlombok.org/)
4. [Cucumber-TestNG](https://cucumber.io/docs/guides/parallel-execution/#testng)