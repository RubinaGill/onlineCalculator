Feature: Subtraction
  User want to perform subtraction
  Scenario Outline: Subtraction cases for two numbers
    Given User uses the online-calculator
    When User have entered <number1> into the calculator
    And User subtract <number2>
    Then result should be <expectedResult> on the screen
    Examples:
      | number1         | number2         | expectedResult |
      | "30"            | "10"            | "20"           |
      | "30"            | "0"             | "30"           |
      | "-30"           | "0"             | "-30"          |
      | "-30"           | "-30"           | "-60"          |
      | "30.04"         | "20.10"         | "9.94"         |
      | "30"            | "20.10"         | "9.9"          |
      | "30.10"         | "20"            | "10.1"        |
      | "30.1111111"    | "20.1000000"    | "10.0111111"   |
      | "309999999"     | "209999999"     | "100 000 000"    |
      | "-999999999"    | "-999999999"    | "-2e+9"        |
      | "999999999"     | "999999999"     | "0"            |
