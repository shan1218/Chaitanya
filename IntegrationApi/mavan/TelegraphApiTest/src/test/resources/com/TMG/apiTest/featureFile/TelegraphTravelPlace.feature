Feature: Create Search Travel Place

  @api
  Scenario Outline: Create Place and Hotels
    Given I Initialize Travel Page
    Then I delete a Place "<placeA>" "<placeB>" "<placeC>"
    Then I create a Place "<placeA>"
    Then I create a Place "<placeB>"
    Then I create a Place "<placeC>"
    When I create Hotel A with locations containing Place B "<placeB>"

    Examples:
      | placeA | placeB | placeC |
      | A      | B      | C      |

  @api
  Scenario Outline: Adding the relation ship between places
    Given I Initialize Travel Page
    When I add relationship between First Place A "<placeA>" and Second Place B "<placeB>" , Place A as the parent of Place B
    Then Hotel A should be available on Place A "<placeA>" Hotel searches
    Then Hotel A should be available on Place B "<placeB>" Hotel searches

    Examples:
      | placeA | placeB | placeC |
      | A      | B      | C      |


  @api
  Scenario Outline: Deleting the relation between places
    Given I Initialize Travel Page
    When I remove relationship between Place A "<placeA>" and Second Place B "<placeB>" , remove Place A as the parent of Place B
    Then Hotel A should not be available on Place B "<placeB>"Hotel searches
    Examples:
      | placeA | placeB | placeC |
      | A      | B      | C      |



  @api
  Scenario Outline:Adding the relation ship between multpiple places
    Given I Initialize Travel Page
    When I add relationship between Place A "<placeA>" and Third Place C "<placeC>", Place C as a parent of Place B
    Then Hotel A should be available on Place A "<placeA>" Hotel searches
    Then Hotel A should be available on Place B "<placeB>" Hotel searches
    Then Hotel A should be available on Place C "<placeC>" Hotel searches

    Examples:
      | placeA | placeB | placeC |
      | A      | B      | C      |

  @api
  Scenario Outline: Deleting the relation between multiple places
    Given I Initialize Travel Page
    When I remove relationship between Place A "<placeA>" and Third Place C "<placeC>", remove Place C as a parent of Place B
    Then Hotel A should not be available on Place C "<placeC>"  Hotel searches
    Examples:
      | placeA | placeB | placeC |
      | A      | B      | C      |



