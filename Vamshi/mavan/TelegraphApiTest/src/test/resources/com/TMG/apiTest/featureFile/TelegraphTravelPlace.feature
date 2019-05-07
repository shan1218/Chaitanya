Feature: Find Travel Place

  Scenario Outline: Travel Place Detail
    Given I access the place by flakeId "<placeDetail>"
    Examples:
      |placeDetail|
      |TravelPlace|