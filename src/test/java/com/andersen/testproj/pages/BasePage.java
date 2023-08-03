package com.andersen.testproj.pages;

import com.andersen.testproj.configuration.Hooks;
import com.andersen.testproj.ui.AppElement;
import com.andersen.testproj.utils.PropertiesUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BasePage {

    private final AppElement snackbarMessage = new AppElement("Snackbar message",
            AppiumBy.id("com.poqstudio.app.platform.yoursclothing:id/snackbar_text"));
    private final WebDriverWait wait;
    protected final Logger logger = LogManager.getLogger();
    protected final AppiumDriver driver;

    public BasePage() {
        driver = Hooks.getDriver();
        wait = getWaiter(PropertiesUtil.getPropertyValueInt("timeouts.element-wait"));
    }

    private WebDriverWait getWaiter(int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.pollingEvery(Duration.ofMillis(250))
                .withTimeout(Duration.ofSeconds(timeoutSeconds));
        return wait;
    }

    public String getSnackbarMessageIfPresent() {
        return isPresent(snackbarMessage) ? getText(snackbarMessage) : "";
    }

    protected String getText(AppElement element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element.getLocator())).getText();
    }

    protected void click(AppElement element) {
        logger.info("[Click] - " + element.getDescription());
        wait.until(ExpectedConditions.elementToBeClickable(element.getLocator()))
                .click();
    }

    protected String getAttribute(AppElement element, String attributeName) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element.getLocator()))
                .getAttribute(attributeName);
    }

    protected boolean isPresent(AppElement element) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(element.getLocator())) != null;
        } catch (TimeoutException e) {
            logger.info(String.format("Element '%s' is not present", element.getDescription()));
            return false;
        }
    }

    protected boolean isDisplayed(AppElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element.getLocator())) != null;
        } catch (TimeoutException e) {
            logger.info(String.format("Element '%s' is not visible", element.getDescription()));
            return false;
        }
    }

    protected void type(AppElement element, String text) {
        logger.info(String.format("Type '%s' to '%s' field", text, element.getDescription()));
        wait.until(ExpectedConditions.presenceOfElementLocated(element.getLocator())).sendKeys(text);
    }

    protected void triggerSearch() {
        ((JavascriptExecutor) driver).executeScript("mobile: performEditorAction",
                new HashMap<String, String>() {{
                    put("action", "Search");
        }});
    }

    /**
     *
     * @param scrollLength 1.0 is 100% of scroll length
     */
    protected void scrollUp(double scrollLength) {
        Dimension size = driver.manage().window().getSize();
        int startX, endX;
        startX = endX = (size.width / 2);
        int startY = (int) (size.height * 0.30);
        int endY = (int) (size.height * 0.70 - 0.4 * (1 - scrollLength));

        scroll(startX, startY, endX, endY);
    }

    /**
     *
     * @param scrollLength 1.0 is 100% of scroll length
     */
    protected void scrollDown(double scrollLength) {
        Dimension size = driver.manage().window().getSize();
        int startX;
        int endX;
        startX = endX = (size.width / 2);
        int startY = (int) (size.height * 0.7);
        int endY = (int) (size.height * 0.3 + 0.4 * (1 - scrollLength));

        scroll(startX, startY, endX, endY);
    }

    protected void scroll(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(finger, PropertiesUtil.isAndroid() ? 0 : 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(0));
        swipe.addAction(new Pause(finger, Duration.ofMillis(300)));
        driver.perform(Collections.singletonList(swipe));
    }

    protected void scrollToElement(String elementText) {
        if (PropertiesUtil.isAndroid()) {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
                    + elementText + "\").instance(0))"));
        } else {
            //todo ios scroll
        }
    }

    protected List<WebElement> getElements(AppElement element) {
        return getElements(element, PropertiesUtil.getPropertyValueInt("timeouts.element-wait"));
    }

    protected WebElement getWebElement(AppElement element) {
        return  wait.until(ExpectedConditions.presenceOfElementLocated(element.getLocator()));
    }

    protected List<WebElement> getElements(AppElement element, int waitSeconds) {
        List<WebElement> elements;
        try {
            elements = getWaiter(waitSeconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(element.getLocator()));
        } catch (TimeoutException e) {
            elements = Collections.emptyList();
            logger.info(String.format("There are no '%s' elements in %s seconds", element.getDescription(), waitSeconds));
        }
        return elements;
    }

    protected List<String> getElementsTexts(AppElement element) {
        return getElementsTexts(element, PropertiesUtil.getPropertyValueInt("timeouts.element-wait"));
    }

    protected List<String> getElementsTexts(AppElement element, int waitSeconds) {
        return getElements(element, waitSeconds).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
