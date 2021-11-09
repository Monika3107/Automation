Feature: Login

Scenario: Login
Given user is already on the Home page
When user clicks on login tab
And user enters below credentials
| username | monika |
| password | letmein |
And user clicks on login button
Then user successfully logs in as "monika"