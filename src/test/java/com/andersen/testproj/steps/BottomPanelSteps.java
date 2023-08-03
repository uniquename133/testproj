package com.andersen.testproj.steps;

import com.andersen.testproj.ui.appElements.BottomPanel;
import io.cucumber.java.en.When;

public class BottomPanelSteps {

    private final BottomPanel bottomPanel;

    public BottomPanelSteps() {
        bottomPanel = new BottomPanel();
    }

    @When("User click wishlist button at bottom panel")
    public void clickSearchButton() {
        bottomPanel.clickWishlistButton();
    }
}
