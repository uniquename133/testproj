package com.andersen.testproj.steps;

import com.andersen.testproj.ui.appElements.BottomPanel;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.stream.Collectors;
import com.andersen.testproj.pages.WishlistPage;
import org.junit.jupiter.api.Assertions;

public class WishlistSteps {

    private final BottomPanel bottomPanel;
    private final WishlistPage wishlistPage;
    private final ScenarioContext scenarioContext;

    public WishlistSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
        bottomPanel = new BottomPanel();
        wishlistPage = new WishlistPage();
    }

    @Then("User see {int} item at wishlist page")
    public void verifyItemsAmountAtWishlistPage(int expectedItemsAmount) {
        Assertions.assertEquals(expectedItemsAmount, wishlistPage.getListItemsAmount(),
                "Not expected amount of wishlist items is displayed");
        Assertions.assertEquals(expectedItemsAmount, wishlistPage.getItemsCountFromLabel(),
                "Not expected amount of wishlist items count is displayed at label");
        Assertions.assertEquals(expectedItemsAmount, bottomPanel.getCountOfWishlistButton(),
                "Not expected amount of wishlist items count is displayed near wishlist button at bottom panel");
    }

    @Then("User see all items added to wishlist")
    public void verifyAllItemsAddedToWishlist() {
        List<String> expectedProducts = scenarioContext.productsAddedToWishlist.stream().sorted()
                .collect(Collectors.toList());
        List<String> actualProducts = wishlistPage.getItemNames().stream().sorted()
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedProducts, actualProducts, "Products in wishlist are not expected");
    }
}
