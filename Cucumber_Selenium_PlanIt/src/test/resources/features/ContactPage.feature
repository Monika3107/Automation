
Feature: Validate Contact Page

Background:
Given User is already on the Home page

Scenario: Validate errors on Contact page
Given User navigates to Contact page from Home page
When User clicks on submit button
Then Validate errors
When User populates mandatory fields
| Forename | Email                        | Message                     | 
| Monika   | solankimonika31@gmail.com    | Hi this is a sample message |
Then Check Validation errors are gone

Scenario: Validate successful submission of feedback on Contact Page
Given User navigates to Contact page from Home page
When User populates mandatory fields
| Forename | Email                        | Message                     | 
| Monika   | solankimonika31@gmail.com    | Hi this is a sample message |
And User clicks on submit button
Then Validate successful submission
