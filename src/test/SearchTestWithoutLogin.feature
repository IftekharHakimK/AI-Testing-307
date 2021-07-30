Feature: Checking Search Without Logging In

  Scenario Outline: "<keyword>" should exactly match in a search
    Given  Browser is opened and maximized
    When Go to goodreads home page
    When Place "<keyword>" in searchbox
    When "<keyword>" exactly exists in table
    Then End

    Examples:
      | keyword        |
      | গীতাঞ্জলি      |
      | 1984           |
      | মেঘনাদবধ কাব্য |

  Scenario Outline: Some "<keyword>" should appear in a search
    Given  Browser is opened and maximized
    When Go to goodreads home page
    When Place "<keyword>" in searchbox
    When "<keyword>" exists in table
    Then End

    Examples:
      | keyword           |
      | Quantum Computing |
      | Software          |
      | Design Pattern    |


