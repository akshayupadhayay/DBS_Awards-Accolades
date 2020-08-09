@SocialImpactScenario
Feature: Learn More About DBS Singapore
  @SocialImpactTest
  Scenario Outline: Navigate to DBS Learn More page to collect F&B Businesses data and store in a excel file
    Given Browser is Open
    And User is already on Sustainability Creating Social Impact page
    When <City> F&B businesses data is available and displayed for user
    Then Read & retrieve the table data from cells
    Then Copy and Store the data into a database(Excel)
    Then Save the file under folder structure
    Then Validate the file size is not zero
    And Navigate to About Page

    Examples:
      |City|
      |Singapore|

