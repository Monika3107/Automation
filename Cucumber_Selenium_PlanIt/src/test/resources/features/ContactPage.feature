Feature: Validate Contact Page

  Background: 
    Given user is already on the Home page

 
  Scenario: Validate errors on Contact page
    Given User navigates to Contact page from Home page
    When User clicks on submit button
    #Then Validate the errors for "MainError" "Forename" "Email" "Message"
    Then the following errors appear
      | Field    | Error Message        |
      | Forename | Forename is required |
      | Email    | Email is required    |
      | Message  | Message is required  |
    When user populates contact fields
      | Forename | Monika          |
      | Email    | asds@asdasd.asd |
      | Message  | asdhlds         |
    Then Check Validation errors are gone

  Scenario: Validate successful submission of feedback on Contact Page
    Given User navigates to Contact page from Home page
    When user populates contact fields
      | Forename | Monika          |
      | Email    | asds@asdasd.asd |
      | Message  | asdhlds         |
    And User clicks on submit button
    Then Validate successful submission message

  Scenario: Validate invalid data inputs in Contact Page
    Given User navigates to Contact page from Home page
    When user populates contact fields
      | Forename | Monika    |
      | Email    | asds@asda |
      | Message  | asdhlds   |
    Then the following errors appear
      | Field | Error Message              |
      | Email | Please enter a valid email |
