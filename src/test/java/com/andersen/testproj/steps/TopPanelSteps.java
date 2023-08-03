package com.andersen.testproj.steps;

import com.andersen.testproj.ui.appElements.TopPanel;
import io.cucumber.java.en.When;

public class TopPanelSteps {

    private final TopPanel topPanel;

    public TopPanelSteps() {
        topPanel = new TopPanel();
    }

    @When("User click search button on top panel")
    public void clickSearchButton() {
        topPanel.clickSearchButton();
    }

    @When("User click home button on top panel")
    public void clickHomeButton() {
        topPanel.clickHomeButton();
    }
}
