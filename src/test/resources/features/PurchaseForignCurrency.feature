@regression
Feature: Purchase Foreign Currency

  Scenario: Available currencies
    Given the user is logged in
    And the user is on "Pay Bills" page
    And the user access to the "Purchase Foreign Currency" tab
    When the user clicks on Currency tab
    Then following currencies should be available
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Singapore (dollar)    |

  @smoke
  Scenario: Error message for not selecting currency
    Given the user is logged in
    And the user is on "Pay Bills" page
    And the user access to the "Purchase Foreign Currency" tab
    When user tries to calculate cost without selecting a currency
    Then alert message "Please, ensure that you have filled all the required fields with valid values." should be displayed

  @smoke
  Scenario: Error message for not entering value
    Given the user is logged in
    And the user is on "Pay Bills" page
    And the user access to the "Purchase Foreign Currency" tab
    When user tries to calculate cost without entering a value
    Then alert message "Please, ensure that you have filled all the required fields with valid values." should be displayed