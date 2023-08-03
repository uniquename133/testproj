package com.andersen.testproj.steps;

import io.cucumber.java.en.Given;
import com.andersen.testproj.pages.OnboardingPage;

public class OnboardingSteps {

    private final OnboardingPage onboardingPage;

    public OnboardingSteps() {
        onboardingPage = new OnboardingPage();
    }

    @Given("User skip onboarding")
    public void skipOnboarding(){
        onboardingPage.clickSkipButton();
    }
}
