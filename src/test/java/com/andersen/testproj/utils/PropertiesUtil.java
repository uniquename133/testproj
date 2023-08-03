package com.andersen.testproj.utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesUtil {
    private final static String defaultConfigName = "config.properties";
    private static PropertiesUtil instance;
    private static Properties properties = null;
    private static String propsFileName = null;

    public static PropertiesUtil getInstance() {
        if (instance == null) {
            instance = new PropertiesUtil();
        }
        return instance;
    }

    public static String getPropertyValue(String name) {
        return getPropertyValue(name, defaultConfigName);
    }

    public static int getPropertyValueInt(String name) {
        return Integer.parseInt(getPropertyValue(name));
    }

    private static String getPropertyValue(String name, String propertiesFileName) {
        if (System.getProperty(name) != null) {
            return System.getProperty(name);
        }
        return getInstance().getValueFromConfigFile(name, propertiesFileName);
    }

    private String getValueFromConfigFile(String key, String propertiesFileName) {
        if (properties == null || !propsFileName.equals(propertiesFileName)) {
            properties = loadConfigFile(propertiesFileName);
            propsFileName = propertiesFileName;
        }

        Object objFromFile = properties.getProperty(key);
        if (objFromFile != null) {
            return Objects.toString(objFromFile);
        } else {
            return null;
        }
    }

    private Properties loadConfigFile(String propertiesFileName) {
        try {
            Properties prop = new Properties();
            prop.load(Objects.requireNonNull(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFileName)));
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean isAndroid() {
        return "android".equalsIgnoreCase(getPropertyValue("platform"));
    }
}
