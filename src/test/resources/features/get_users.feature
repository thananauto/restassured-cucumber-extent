Feature: Get the list of users

  @UserLists @200
  Scenario: Verify user can get the users list
    Given I hit the url '/api/users'
    And   I set the header as
            |Content-Type | application/json|
            | Accept      | application/json|
    And   I set the parameter
            |page|2|
    When  I send the GET request
    Then  I get the status code as '200'
    And   I see the response having the 'page' as '2'
    And   I validate the users schema of the response

    @List @200
  Scenario: Verify the single user list
    Given I hit the url '/api/user/2'
      And   I set the header as
        |content-type | application/json|
        | Accept      | application/json|
      When  I send the GET request
      Then  I get the status code as '200'
      And   I see the response having the 'data.id' as '2'
      And   I validate the single_user schema of the response

  @List @404
  Scenario: Verify the invalid user
    Given I hit the url '/api/user/2234'
    And   I set the header as
      |content-type | application/json|
      | Accept      | application/json|
    When  I send the GET request
    Then  I get the status code as '404'
