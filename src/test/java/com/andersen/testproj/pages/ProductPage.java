package com.andersen.testproj.pages;

import com.andersen.testproj.ui.AppElement;
import com.andersen.testproj.utils.PropertiesUtil;
import io.appium.java_client.AppiumBy;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {
    private final static String productInfoLabelTextEng = "Product Info";

    private final AppElement productName = new AppElement("Product name at product page",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/text_title"));
    private final AppElement wishlistIcon = new AppElement("Add to wishlist button at product page",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/favourite_view"));
    private final AppElement colourIcon = new AppElement("Colour icon at product page",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/item_picker"));

    public ProductPage clickWishlistIcon() {
        scrollUp(0.5);
        click(wishlistIcon);
        return this;
    }

    public String getProductName() {
        return getText(productName);
    }

    public List<String> getColours() {
        scrollToElement(productInfoLabelTextEng);
        return getElements(colourIcon, PropertiesUtil.getPropertyValueInt("timeouts.element-wait-short"))
                .stream().map(el -> el.getAttribute("content-desc"))
                .collect(Collectors.toList());
    }

    public ProductPage pickColour(int colourIndex) {
        getElements(colourIcon).get(colourIndex - 1).click();
        return this;
    }
}
