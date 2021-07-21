Feature: Checking page
  Scenario: Checking incorrect login
    Given Browser is opened and maximized
    When Go to goodreads login page
    When Incorrect credentials placed
    When Error message visible and browser still at login page
    Then End
  Scenario: Checking correct login
    Given Browser is opened and maximized
    When Go to goodreads login page
    When Correct credentials placed
    When Homepage visible
    Then End
