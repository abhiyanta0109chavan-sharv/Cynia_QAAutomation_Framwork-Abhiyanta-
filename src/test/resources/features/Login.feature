Feature: Cynia Login

  Scenario: Login with valid credentials
    Given the user is on the Cynia login page
    When the user enters valid username and password
    And the user clicks on the Sign In button
    Then the user should be successfully logged in

  Scenario: Login with invalid credentials
    Given the user is on the Cynia login page
    When the user enters invalid username and password
    And the user clicks on the Sign In button
    Then the appropriate login error message should be displayed

  Scenario: Verify Sign In button is disabled when username and password are blank
    Given the user is on the Cynia login page
    When the user leaves the username and password fields blank
    Then the Sign In button should be disabled

  Scenario: Login with invalid username and valid password
    Given the user is on the Cynia login page
    When the user enters invalid username and valid password
    And the user clicks on the Sign In button
    Then the appropriate login error message should be displayed