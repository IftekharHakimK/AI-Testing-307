Feature: Checking page

  Background: Going to Login Page
    Given Browser is opened and maximized
    When Go to goodreads login page

  Scenario: Checking incorrect login
    When Incorrect credentials placed
    When Error message visible and browser still at login page
    Then End

  Scenario: Checking correct login
    When Correct credentials placed
    When Homepage visible
    Then End

  Scenario: Logging Out
    When Correct credentials placed
    When Log out
    Then End

  Scenario Outline: Upload "<file>" as Profile Picture
    When Correct credentials placed
    Then Upload "<file>" as Profile Picture
    Then End
    Examples:
      | file          |
      | BUET_LOGO.png |
      | Flag_Bangladesh.jpg|

  Scenario Outline: Upload invalid "<file>" as Profile Picture
    When Correct credentials placed
    Then Upload invalid "<file>" as Profile Picture
    Then End
    Examples:
      | file          |
      | t1.txt |

