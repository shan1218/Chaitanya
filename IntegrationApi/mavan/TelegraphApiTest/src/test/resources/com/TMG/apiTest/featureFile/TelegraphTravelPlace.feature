Feature: Create Search Travel Place

  @api
  Scenario Outline: Create Places and Hotels
    Given I Initialize Travel Page "<createPlace>"
    Then I Create A First Place
    Then I Create A Second Place
    Then I Create A Third Place
    When I Create Hotel A with locations containing Place B

    Examples:
      | createPlace |
      | CreatePlace |


  @api
  Scenario Outline: Search Created Hotels and Places
    Given I Initialize Travel Page "<createPlace>"
    When I add relationship between First Place A and Second Place B , Place A as the parent of Place B
    Then Hotel A should be available on Place B Hotel searches
    Then Hotel A should be available on Place A Hotel searches

    Examples:
      | createPlace |
      | CreatePlace |

  @api
  Scenario Outline: Searching Created Multpile Hotels and Places
    Given I Initialize Travel Page "<createPlace>"
    When I add relationship between Place A and Place X, Place X as a parent of Place B
    Then Hotel A should be available on Place B Hotel searches
    Then Hotel A should be available on Place X Hotel searches
    Then Hotel A should be available on Place A Hotel searches

    Examples:
      | createPlace |
      | CreatePlace |

  @api
  Scenario Outline: Searching Deleted Multpile Hotels and Places
    Given I Initialize Travel Page "<createPlace>"
    When I remove relationship between Place A and Place X, remove Place X as a parent of Place B
    Then Hotel A should not be available on Place X Hotel searches
    Examples:
      | createPlace |
      | CreatePlace |


