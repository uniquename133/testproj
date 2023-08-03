package com.andersen.testproj.steps;

import com.andersen.testproj.data.SnackbarMessage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.andersen.testproj.pages.ProductPage;
import org.junit.jupiter.api.Assertions;

public class ProductSteps {

    private final ProductPage productPage;
    private final ScenarioContext scenarioContext;

    public ProductSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        productPage = new ProductPage();
    }

    @When("User pick {int}nd colour if its available")
    public void pickColourIfPresent(int colourIndex) {
       if (productPage.getColours().size() >= colourIndex) {
           productPage.pickColour(colourIndex);
       }
    }

    @When("User click on wishlist icon")
    public void clickWishlistIcon() {
        productPage.clickWishlistIcon();
        scenarioContext.productsAddedToWishlist.add(productPage.getProductName());
    }

    @Then("User see 'Added to wishlist' snackbar message at product page")
    public void addedToWishlistMessageIsPresent() {
        Assertions.assertEquals(SnackbarMessage.ADDED_TO_WISHLIST, productPage.getSnackbarMessageIfPresent(),
                "Expected snackbar message is not displayed");
    }
}
