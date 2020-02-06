Feature: Division
User wants to perform division

  Scenario Outline: Division cases for two numbers
    Given User uses the online-calculator
    When User have entered <dividend> into the calculator
    And User divides by <divisor>
    Then result should be <expectedResult> on the screen
    Examples:
      | dividend      | divisor       | expectedResult |
      | "30"          | "10"          | "3"            |
      | "10"          | "30"          | "0.33333333"   |
      | "-30"         | "10"          | "-3"           |
      | "-30"         | "-10"         | "3"            |
      | "30"          | "-10"         | "20"           |
      | "30.30"       | "10"          | "3.03"         |
      | "30.30"       | "10.10"       | "3"            |
      | "-30.30"      | "10"          | "-3.03"        |
      | "-30.30"      | "99999999"    | "-3.03e-8"     |
      | "10"          | "30.30"       | "0.330033"     |
      | "999999999"   | "999999999"   | "1"            |
      | "0"           | "0"           | "Error"        |
      | "0"           | "1"           | "0"            |
