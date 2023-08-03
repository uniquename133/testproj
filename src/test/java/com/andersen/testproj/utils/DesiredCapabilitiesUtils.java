package com.andersen.testproj.utils;

import java.io.File;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesUtils {

    public static DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", new File(PropertiesUtil.getPropertyValue("app-path")).getAbsolutePath());
        capabilities.setCapability("deviceName", PropertiesUtil.getPropertyValue("android.device-name"));
        capabilities.setCapability("platformName", PropertiesUtil.getPropertyValue("platform"));
        capabilities.setCapability("appium:automationName", PropertiesUtil.getPropertyValue("android.automation-name"));
        capabilities.setCapability("appium:appWaitForLaunch", false);
        capabilities.setCapability("autoGrantPermissions", true);

        return capabilities;
    }

    public static DesiredCapabilities getIosCapabilities() {
        return null;
    }
}
