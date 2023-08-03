package com.andersen.testproj.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {
    private static final Set<String> CONFIG_FILES;
    private static final Properties PROPERTIES;

    static {
        CONFIG_FILES = new HashSet<>();
        CONFIG_FILES.add("config.properties");

        PROPERTIES = new Properties();
        CONFIG_FILES.forEach(file -> {
            Properties loaded = loadConfigFile(file);
            PROPERTIES.putAll(loaded);
        });
    }

    public static String getPropertyValue(String name) {
        return getProperty(name);
    }

    public static int getPropertyValueInt(String name) {
        return Integer.parseInt(getProperty(name));
    }

    public static boolean isAndroid() {
        return "android".equalsIgnoreCase(getPropertyValue("platform"));
    }

    private static String getProperty(String name) {
        String sysProp = System.getProperty(name);
        if (Objects.nonNull(sysProp)) {
            return sysProp;
        }

        String loadedProp = PROPERTIES.getProperty(name);
        if (Objects.nonNull(loadedProp)) {
            return loadedProp;
        } else {
            throw new RuntimeException(String.format("There is no property with name '%s'", name));
        }
    }

    private static Properties loadConfigFile(String propertiesFileName) {
        try {
            Properties prop = new Properties();
            prop.load(Objects.requireNonNull(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFileName)));
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
