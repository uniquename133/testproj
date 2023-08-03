package com.andersen.testproj.pages;

import com.andersen.testproj.ui.AppElement;
import io.appium.java_client.AppiumBy;

public class HomePage extends BasePage {
    private final AppElement mainBanner = new AppElement("Main banner at home page",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/home_banner_item_view_image_view"));

    public boolean isMainBannerDisplayed() {
        return isDisplayed(mainBanner);
    }
}
