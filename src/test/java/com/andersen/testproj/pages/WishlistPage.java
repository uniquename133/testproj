package com.andersen.testproj.pages;

import com.andersen.testproj.ui.AppElement;
import io.appium.java_client.AppiumBy;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class WishlistPage extends BasePage {

    private final AppElement wishlistItemsCountLabel = new AppElement("Wishlist items count label",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/fragment_wish_list_products_count"));
    private final AppElement wishlistProductName = new AppElement("Wishlist product name",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/item_wish_list_title_text_view"));
    private final AppElement listItem = new AppElement("Wishlist item", AppiumBy.id("com.poqstudio.app.platform"
            + ".yoursclothing:id/item_wish_list_content"));

    public int getItemsCountFromLabel() {
        return Integer.parseInt(getText(wishlistItemsCountLabel).split(" ")[0]);
    }

    public int getListItemsAmount() {
        return getElements(listItem).size();
    }

    public List<String> getItemNames() {
        List<String> previousItemNames = getElementsTexts(wishlistProductName);
        LinkedHashSet<String> itemNames = new LinkedHashSet<>(previousItemNames);
        int maxScrolls = 100;
        for (int i = 0; i < maxScrolls; i++) {
            scrollDown(0.5);
            List<String> currentItemNames = getElementsTexts(wishlistProductName);
            if (currentItemNames.equals(previousItemNames)) {
                break;
            } else {
                itemNames.addAll(currentItemNames);
                previousItemNames = currentItemNames;
            }
        }
        return new ArrayList<>(itemNames);
    }
}
