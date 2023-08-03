package com.andersen.testproj.pages;

import com.andersen.testproj.ui.AppElement;
import io.appium.java_client.AppiumBy;

public class OnboardingPage extends BasePage {
    private final AppElement skipButton = new AppElement("Onboarding page skip button",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/onboarding_page_skip_button"));

    public HomePage clickSkipButton() {
        click(skipButton);
        return new HomePage();
    }
}
