Feature: Division
User wants to perform division

  Scenario Outline: Division cases for two numbers
    Given User uses the online-calculator
    When User have entered <number1> into the calculator
    And User divides by <number2>
    Then result should be <expectedResult> on the screen
    Examples:
      | number1 | number2 | expectedResult |
      | "30"    | "10"    | "3"            |
      | "10"    | "30"    | "0"            |
      | "-30"    | "10"    | "-3"            |
      | "-30"    | "-10"    | "3"            |
      | "30"    | "-10"    | "3"            |
      | "30.30"    | "10"    | "3.03"            |
      | "30.30"    | "10.10"    | "3"            |
      | "10"    | "30.30"    | "0.330033"            |
