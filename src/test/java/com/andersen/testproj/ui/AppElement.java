package com.andersen.testproj.ui;

import com.andersen.testproj.utils.PropertiesUtil;
import org.openqa.selenium.By;

public class AppElement {

    private final String description;
    private final By androidLocator;
    private final By iosLocator;

    public AppElement(String description, By androidLocator, By iosLocator) {
        this.description = description;
        this.androidLocator = androidLocator;
        this.iosLocator = iosLocator;
    }

    public AppElement(String description, By androidLocator) {
        this(description, androidLocator, null);
    }

    public String getDescription() {
        return description;
    }

    public By getLocator() {
        return PropertiesUtil.isAndroid() ? androidLocator : iosLocator;
    }
}
