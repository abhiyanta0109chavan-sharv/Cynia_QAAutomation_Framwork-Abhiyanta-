package com.cynia.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(
                            "src/test/resources/config/config.properties");

            properties.load(fileInputStream);
            fileInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load config.properties", e);
        }
    }

    public static String getProperty(String key) {

        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "Property not found in config.properties: " + key);
        }

        return value;
    }
}
