
Feature: Validate Shop Page

Background:
Given User is already on the Home page

#@SmokeTest
Scenario: Validate items in Cart
Given User navigates to Shop page from Home page
When User Click on items to add in cart
| Item         | Number |
| Funny Cow    | 2      |
| Fluffy Bunny | 1      |
And User clicks on Cart menu
Then User verifies items are in Cart 
| Item         | Number |
| Funny Cow    | 2      |
| Fluffy Bunny | 1      |

