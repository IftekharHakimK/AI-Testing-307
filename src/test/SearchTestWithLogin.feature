
Feature: Checking Search With Logged In

  Background: Logged in
    Given Browser is opened and maximized
    When Go to goodreads login page
    When Correct credentials placed
    When Homepage visible

  Scenario Outline: "<keyword>" should exactly match in a search
    When Place "<keyword>" in searchbox
    When "<keyword>" exactly exists in table
    Then End

    Examples:
      | keyword        |
      | গীতাঞ্জলি      |
      | 1984           |
      | মেঘনাদবধ কাব্য |

  Scenario Outline: Some "<keyword>" should appear in a search
    When Place "<keyword>" in searchbox
    When "<keyword>" exists in table
    Then End

    Examples:
      | keyword           |
      | Quantum Computing |
      | Software          |
      | Design Pattern    |


