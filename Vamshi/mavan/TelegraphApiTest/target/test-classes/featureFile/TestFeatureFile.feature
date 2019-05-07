Feature: International Space Station Current Location

  @Logon_Admin_user
  Scenario: Retrieve International Space Station Current Location
    Given I access the ISS Current Location
    When I retrieve the ISS Current Location
    Then I see the ISS Current Location