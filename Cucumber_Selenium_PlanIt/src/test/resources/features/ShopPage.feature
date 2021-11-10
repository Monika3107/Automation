Feature: Validate Shop Page

  Background: 
    Given user is already on the Home page

  Scenario: Validate items in Cart
    Given User navigates to Shop page from Home page
    When User Click on items to add in cart
      | Item         | Quantity |
      | Funny Cow    |        2 |
      | Fluffy Bunny |        1 |
    And user clicks on Cart Tab
    Then User verifies items are in Cart
      | Item         | Quantity |
      | Funny Cow    |        2 |
      | Fluffy Bunny |        1 |

  #exercise 10
  Scenario: Validate cart
    Given User navigates to Shop page from Home page
    When User Click on items to add in cart
      | Item          | Quantity |
      | Funny Cow     |        1 |
      | Handmade Doll |        1 |
      | Fluffy Bunny  |        1 |
    And user clicks on Cart Tab
    Then user removes item "Handmade Doll" from cart
    And user verifies item "Handmade Doll" is removed
    And user verifies other items are in cart
      | Funny Cow    |
      | Fluffy Bunny |

  #exercise 11
    @SmokeTest
  Scenario: Price validation
    Given User navigates to Shop page from Home page
    When User Click on items to add in cart and store price
      | Item           | Quantity |
      | Stuffed Frog   |        1 |
      | Valentine Bear |        1 |
    Then user clicks on Cart Tab
    And user verifies pricing of items in the cart
      |  Item           | Quantity |
      | Stuffed Frog   |        1 |
      | Valentine Bear |        1 |
      
  #exercise 12
  @SmokeTest
  Scenario: Update Quantity Validation Test
    Given User navigates to Shop page from Home page
    When User Click on items to add in cart and store price
      | Item           | Quantity |
      | Stuffed Frog   |        1 |
      | Valentine Bear |        1 |
    And user clicks on Cart Tab
    And user updates quantity of items in cart
      | Item           | Quantity |
      | Stuffed Frog   |        5 |
      | Valentine Bear |       11 |
    And user verifies pricing of items in the cart
      | Item           | Quantity |
      | Stuffed Frog   |        5 |
      | Valentine Bear |       11 |
