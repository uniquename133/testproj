package com.andersen.testproj.steps;

import com.andersen.testproj.ui.appElements.BottomPanel;
import io.cucumber.java.en.Then;
import com.andersen.testproj.pages.HomePage;
import org.junit.jupiter.api.Assertions;

public class HomeSteps {

    private final HomePage homePage;
    private final BottomPanel bottomPanel;

    public HomeSteps() {
        homePage = new HomePage();
        bottomPanel = new BottomPanel();
    }

    @Then("User is on Home page")
    public void userIsOnHomePage() {
        Assertions.assertTrue(homePage.isMainBannerDisplayed(), "Main banner of home page is not displayed");
        Assertions.assertTrue(bottomPanel.isHomeButtonSelected(), "Home button is not selected at bottom panel");
    }
}
