Feature: CE
  User wants to clear last input entry

  Scenario: Default view of CE
    Given User uses the online-calculator
    Then "CE" button must be visible

  Scenario Outline:
    Given User uses the online-calculator
    When User have entered <number1> into the calculator
    Then <buttonCE> should change to <buttonC>
    Examples:
      | number1 | buttonCE | buttonC |
      | "30"    | "CE"    | "C"     |

  Scenario Outline:
    Given User uses the online-calculator
    When User have entered <number1> into the calculator
    And  User have entered <buttonC> into the calculator
    Then result should be "0" on the screen
    And  <buttonC> should change to <buttonCE>

    Examples:
      | number1 | buttonC | buttonCE |
      | "30"    | "C"    | "CE"     |
