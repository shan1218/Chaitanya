Feature: Create Search Travel Place

  @api
  Scenario Outline: Create Place
    Given I Initialize Travel Page
    Then I delete a Place "<placeA>" "<placeB>" "<placeC>"
    Then I create a Place "<placeA>"
    Then I create a Place "<placeB>"
    Then I create a Place "<placeC>"
    When I create Hotel A with locations containing Place B "<placeB>"

    Examples:
      | placeA | placeB | placeC |
      | A      | B      | C      |


  Scenario Outline: Search Place
    Given I Initialize Travel Page
    When I add relationship between First Place A "<placeA>" and Second Place B "<placeB>" , Place A as the parent of Place B
    Then Hotel A should be available on Place A "<placeA>" Hotel searches
    Then Hotel A should be available on Place B "<placeB>" Hotel searches

    Examples:
      | placeA | placeB | placeC |
      | A      | B      | C      |


  @api
  Scenario Outline: Searching Created Multpile Hotels and Places
    Given I Initialize Travel Page "<createPlace>"
    When I add relationship between Place A "<placenameA>" and Place C, Place C as a parent of Place B
    Then Hotel A should be available on Place B Hotel searches
    Then Hotel A should be available on Place C Hotel searches
    Then Hotel A should be available on Place A Hotel searches

    Examples:
      | createPlace |
      | CreatePlace |

  @api
  Scenario Outline: Searching Deleted Multpile Hotels and Places
    Given I Initialize Travel Page "<createPlace>"
    When I remove relationship between Place A and Place C, remove Place C as a parent of Place B
    Then Hotel A should not be available on Place C  Hotel searches
    Examples:
      | createPlace |
      | CreatePlace |