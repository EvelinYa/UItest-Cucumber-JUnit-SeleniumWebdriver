@CriticalPath
Feature: Purchase a Product
  As a customer
  I want to be able to purchase the Product
  So that I can specify my delivery and payment details and place the Order

Scenario: User can add a product to the cart
  Given I am on the Yakaboo website
  When I search for "Емоційний інтелект" Product
  And I select "Емоційний інтелект" Product from the search results
  And I add the Product to the Cart and proceed to the Cart
  And I check the Product in the Cart and go to the Checkout page
  And I add all the Checkout information and Submit the Order
  And I add all the Payment information and Pay for the Order
  Then I should see Order Confirmation Page

