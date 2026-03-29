Feature: Login

  Scenario Outline: Successful login on different browsers
    Given user opens application on "<browser>"
    When user logs in with valid credentials
    Then inventory page is displayed

    Examples:
      | browser |
      | chrome  |
      | edge    |