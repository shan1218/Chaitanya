Feature: Create Search Travel Place

  @api
  Scenario Outline: Create Place
    Given I Initialize Travel Page "<createPlace>"
    Then I create a Place "<placename>" with type "<type>" lat "<lat>" and long "<long>"
    Then I create a Place "<placename2>" with type "<type2>" lat "<lat2>" and long "<long2>"
    Then I create a Place "<placename3>" with type "<type3>" lat "<lat3>" and long "<long3>"
    When I create Hotel A with locations containing Place B "<placeNameXB>"
    When I add relationship between First Place A "<placenameA>" and Second Place B "<placenameB>" , Place A as the parent of Place B
    Then Hotel A should be available on Place A "<placename2A>" Hotel searches
    Then Hotel A should be available on Place B "<placename2B>" Hotel searches

    Examples:
      | createPlace | placename | type | lat  | long | placename2 | type2   | lat2 | long2 | placename3 | type3   | lat3 | long3 | placeNameXB | placenameA | placenameB | placename2A | placename2B |
      | CreatePlace | placeA    | city | 0.01 | 0.01 | placeB     | Address | 0.02 | 0.02  | placeC     | Address | 0.03 | 0.03  | placeB      | placeA     | placeB     | placeA      | placeB      |


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


