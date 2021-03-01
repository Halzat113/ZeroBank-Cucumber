@regression
@smoke
Feature: As a user, I should be able to login


  Scenario: login with valid credentials
    Given user is on the login page
    When login with valid credentials
    Then title should be "Zero - Free Access to Online Banking"


  Scenario Outline: login with Invalid credentials
    Given user is on the login page
    When user enters "<username>" and "<password>"
    Then error message "Login and/or password are wrong." should be displayed
    Examples:
      | username | password |
      | user     | pass     |
      |          | pass     |
      | user     | password |
      | username | passwoRD |