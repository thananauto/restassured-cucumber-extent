Feature: Verify user can validate the valid login scenarios

  @Login
  Scenario: Validate the login scenario
    Given I hit the url '/api/login'
    And   I set the header as
      |content-type | application/json|
      | Accept      | application/json|
    And   I get the body from login json and set
      |email|eve.holt@reqres.in|
      |password | cityslicka |
    When  I send the POST request
    Then  I get the status code as '200'