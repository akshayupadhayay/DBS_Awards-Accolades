@AwardsAccoladesScenario
Feature: Play with Awards & Accolades page
  @AwardsAccoladesTest
  Scenario Outline: Navigate to Awards & Accolades page and validate the award names and captions
    Given Already on Awards & Accolades page
    And The awards are displayed
    Then Verify the total awards displayed are <awardCount>
    Then Validate all awards names and captions

    Examples:
      |awardCount|
      |22|