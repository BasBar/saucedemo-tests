Feature: Sorting products

  Scenario Outline: User sorts products on different browsers
    Given user logs into the application on "<browser>"
    When user selects "<sortOption>" sorting option
    Then products should be sorted "<order>"

    Examples:
      | browser | sortOption           | order        |
      | chrome  | Name (A to Z)        | asc-name     |
      | edge    | Name (Z to A)        | desc-name    |
      | chrome  | Price (low to high)  | asc-price    |
      | edge    | Price (high to low)  | desc-price   |