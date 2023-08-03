Feature: Wishlist

  Scenario Outline: Add product to wishlist

    Given User skip onboarding
    And User is on Home page
    When User click search button on top panel
    And User search "<keyword>" text in Search page
    And User click 6th result at Search page
    And User pick 2nd colour if its available
    And User click on wishlist icon
    Then User see 'Added to wishlist' snackbar message at product page
    When User click home button on top panel
    And User click wishlist button at bottom panel
    Then User see all items added to wishlist

    Examples:
      |    keyword    |
      |    shirts     |
      |    trousers   |
      |    clothes    |
      |    men        |
      |    women      |
      |    essentials |
      |    jackets    |
      |    hoodie     |