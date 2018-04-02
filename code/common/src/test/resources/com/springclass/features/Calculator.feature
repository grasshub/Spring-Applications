#!--
#!-- Calculator Stories
#!--
#!-- These are all Cuke: B.D.D. stories.
#!-- Calculator.

Feature: Calculate various values.

  In order to verify our Calculator
  As a user
  I want to be able to perform various math operations.

  Scenario: Add two values together.
    Given I want to add '2' and '4'
    Then the result should be '6'

    #-------------------------------------------------------------------------#
  Scenario Outline: Add two values together
    Given I want to add '<valueA>' and '<valueB>'
    Then the result should be '<expected>'

    Examples:
      | valueA  | valueB | expected |
      | 2       | 4      | 6        |
      | 5       | 5      | 10       |
      | 5       | 10     | 15       |

    #-------------------------------------------------------------------------#
  Scenario Outline: Subract two values
    Given I want to subtract '<valueA>' and '<valueB>'
    Then the result should be '<expected>'

    Examples:
      | valueA  | valueB | expected |
      | 2       | 4      | -2       |
      | 5       | 5      | 0        |
      | 10      | 5      | 5        |

    #-------------------------------------------------------------------------#
  Scenario Outline: Multiply two values together
    Given I want to multiply '<valueA>' and '<valueB>'
    Then the result should be '<expected>'

    Examples:
      | valueA  | valueB | expected |
      | 2       | 4      | 8        |
      | 5       | 5      | 25       |
      | 5       | 10     | 50       |
