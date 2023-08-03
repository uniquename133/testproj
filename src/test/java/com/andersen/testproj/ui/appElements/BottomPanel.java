package com.andersen.testproj.ui.appElements;

import com.andersen.testproj.ui.AppElement;
import io.appium.java_client.AppiumBy;
import com.andersen.testproj.pages.BasePage;

public class BottomPanel extends BasePage {
    private final AppElement homeButton = new AppElement("Home button at bottom panel",
            AppiumBy.accessibilityId("Home"));
    private final AppElement shopButton = new AppElement("Shop button at bottom panel",
            AppiumBy.accessibilityId("Shop"));
    private final AppElement swipeToLikeButton = new AppElement("Swipe to like button at bottom panel",
            AppiumBy.accessibilityId("Swipe to like"));
    private final AppElement wishlistButton = new AppElement("Wishlist button at bottom panel",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/navigation_item_four"));
    private final AppElement myAccountButton = new AppElement("My Account button at bottom panel",
            AppiumBy.accessibilityId("My Account"));

    public void clickWishlistButton() {
        click(wishlistButton);
    }

    public int getCountOfWishlistButton() {
        return Integer.parseInt(getAttribute(wishlistButton, "content-desc").split(" ")[3]);
    }

    public boolean isHomeButtonSelected() {
        return Boolean.parseBoolean(getAttribute(homeButton, "selected"));
    }
}
