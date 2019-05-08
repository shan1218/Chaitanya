Feature: Create Search Travel Place

  Scenario Outline: Create and Search Place Detail
    Given I Initialize Travel Page "<createPlace>"
    Then I Create A First Place
    Then I Create A Second Place
    Then I Search A Place "<searchPlace>"
    Examples:
      |createPlace|  |searchPlace|
      |CreatePlace|  |SearchPlace|