Feature: Scenario to validate whether user is getting created

  Scenario: Create a user and validate
    Given I hit the url '/api/users'
    And   I set the header as
      |content-type | application/json|
      | Accept      | application/json|
    And   I get the body from create_user json and set
        |name|thanan|
        |job | testlead |
    When  I send the POST request
    Then  I get the status code as '201'
    And   Save the response 'id'
    Given I hit the url '/api/user/{id}'
    And   I set the header as
      |content-type | application/json|
      | Accept      | application/json|
    When  I send the GET request
    Then  I get the status code as '200'
    And   I see the response having the 'data.id' as '{id}'
    And   I validate the single_user schema of the response