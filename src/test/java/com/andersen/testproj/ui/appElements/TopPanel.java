package com.andersen.testproj.ui.appElements;

import com.andersen.testproj.ui.AppElement;
import io.appium.java_client.AppiumBy;
import com.andersen.testproj.pages.BasePage;
import com.andersen.testproj.pages.HomePage;
import com.andersen.testproj.pages.SearchPage;

public class TopPanel extends BasePage {
    private final AppElement searchButton = new AppElement("Top panel search button",
            AppiumBy.accessibilityId("Search"));
    private final AppElement cartButton = new AppElement("Top panel cart button",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/action_bag"));
    private final AppElement homeButton = new AppElement("Top panel home button",
            AppiumBy.accessibilityId("Home"));

    public boolean isSearchButtonVisible() {
        return isDisplayed(searchButton);
    }

    public SearchPage clickSearchButton() {
        click(searchButton);
        return new SearchPage();
    }

    public HomePage clickHomeButton() {
        click(homeButton);
        return new HomePage();
    }

    public boolean isCartButtonVisible() {
        return isDisplayed(cartButton);
    }
}
