package com.andersen.testproj.pages;

import com.andersen.testproj.ui.AppElement;
import io.appium.java_client.AppiumBy;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchPage extends BasePage {
    private final AppElement searchInput = new AppElement("Search input",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/search_toolbar_edit_text"));
    private final AppElement backButton = new AppElement("Search page 'Back' button",
            AppiumBy.accessibilityId("Back"));
    private final String listItemLabelResourceId = "com.poqstudio.app.platform.yoursclothing:id/product_card_title";
    private final AppElement listElementLabel = new AppElement("Search page list element",
            AppiumBy.id(listItemLabelResourceId));
    private final AppElement noSearchResultsMessage = new AppElement("Search page 'No search results' message",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/error_screen_title_text_view"));


    public SearchPage search(String text) {
        click(searchInput);
        type(searchInput, text);
        triggerSearch();
        return this;
    }

    public SearchPage clickBackButton() {
        click(backButton);
        return this;
    }

    public boolean isNoSearchResultsMessagePresent() {
        return isPresent(noSearchResultsMessage);
    }

    public boolean isSearchResultPresent() {
        return isPresent(listElementLabel);
    }

    public List<String> getDisplayedItems() {
        return getElementsTexts(listElementLabel);
    }

    public List<String> getDisplayedItems(int minimumAmount) {
        Set<String> shownElements = new LinkedHashSet<>(getDisplayedItems());
        int scrollAttempts = 100;
        for (int i = 0; i < scrollAttempts; i++) {
            scrollDown(0.5);
            shownElements.addAll(getDisplayedItems());
            if (shownElements.size() >= minimumAmount) {
                break;
            }
        }

        return new ArrayList<>(shownElements);
    }

    public ProductPage clickListItem(String itemLabel) {
        scrollToElement(itemLabel);
        click(new AppElement(itemLabel + " item at search page",
                AppiumBy.xpath(String.format("//*[@resource-id='%s'][@text=\"%s\"]", listItemLabelResourceId, itemLabel))));
        return new ProductPage();
    }

}
