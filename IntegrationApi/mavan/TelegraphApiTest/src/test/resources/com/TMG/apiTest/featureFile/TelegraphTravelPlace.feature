Feature: Create Search Travel Place

  Scenario Outline: Create and Search Place Detail
    Given I Initialize Travel Page "<createPlace>"
#    Then I Create A First Place
#    Then I Create A Second Place
#    When I Create Hotel A with locations containing Place B
    When I add relationship between First Place A and Second Place B , Place A as the parent of Place B
#    Then Hotel A should be available on Place B Hotel searches
#    Then Hotel A should be available on Place A Hotel searches
    Examples:
      | createPlace |
      | CreatePlace |


