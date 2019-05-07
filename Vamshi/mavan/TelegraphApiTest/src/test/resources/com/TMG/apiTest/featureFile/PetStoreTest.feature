Feature: Testing PetStore Swagger Application

  Scenario Outline: Test PetStore Rest Webservice
    Given I access the pet by status "<testDataName>"
    Then I get all the data by status
    Examples:
      |testDataName|
      |PetStore|