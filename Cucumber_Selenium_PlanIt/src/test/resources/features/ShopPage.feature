
Feature: Validate Shop Page

Background:
Given user is already on the Home page

Scenario: Validate items in Cart
Given User navigates to Shop page from Home page
When User Click on items to add in cart
| Item         | Number |
| Funny Cow    | 2      |
| Fluffy Bunny | 1      |
And user clicks on Cart Tab
Then User verifies items are in Cart 
| Item         | Number |
| Funny Cow    | 2      |
| Fluffy Bunny | 1      |

@SmokeTest
Scenario: Validate cart
Given User navigates to Shop page from Home page
When User Click on items to add in cart
| Item          | Number |
| Funny Cow     | 1      |
| Handmade Doll | 1      |
| Fluffy Bunny  | 1      |
And user clicks on Cart Tab
Then user removes item "Handmade Doll" from cart
And user verifies item "Handmade Doll" is removed
And user verifies other items are in cart 
| Funny Cow     |
| Fluffy Bunny  |
