package com.andersen.testproj.configuration;

import com.andersen.testproj.utils.DesiredCapabilitiesUtils;
import com.andersen.testproj.utils.PropertiesUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Hooks {

    private static ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<>();

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static AppiumDriverLocalService getService() {
        return service.get();
    }

    @Before
    public void startServer() {
        startAppiumServer();

        DesiredCapabilities capabilities = PropertiesUtil.isAndroid() ? DesiredCapabilitiesUtils.getAndroidCapabilities()
                : DesiredCapabilitiesUtils.getIosCapabilities();
        driver.set(new AppiumDriver(service.get().getUrl(), capabilities));
    }

    private void startAppiumServer() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        service.set(appiumServiceBuilder.build());
        service.get().start();

        if (service == null || !service.get().isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
        }
    }

    @After
    public void stopServer() {
        if (driver.get() != null) {
            driver.get().quit();
        }
        if (service.get() != null) {
            service.get().stop();
        }
    }
}
