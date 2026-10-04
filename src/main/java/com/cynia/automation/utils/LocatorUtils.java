package com.cynia.automation.utils;

import org.openqa.selenium.By;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class LocatorUtils {

    private static final Properties LOCATORS =
            new Properties();

    static {

        try (InputStream input =
                     LocatorUtils.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     "config/locators.properties"
                             )) {

            if (input == null) {

                throw new RuntimeException(
                        "locators.properties file not found"
                );
            }

            /*
             * IMPORTANT:
             * locators.properties contains Unicode characters
             * such as the ellipsis (…) in the Search placeholder.
             *
             * Properties.load(InputStream) reads using ISO-8859-1.
             * Therefore we explicitly read the file as UTF-8.
             */
            try (Reader reader =
                         new InputStreamReader(
                                 input,
                                 StandardCharsets.UTF_8
                         )) {

                LOCATORS.load(reader);
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load locators.properties",
                    e
            );
        }
    }

    public static By getLocator(String key) {

        String locatorValue =
                LOCATORS.getProperty(key);

        if (locatorValue == null ||
                locatorValue.trim().isEmpty()) {

            throw new RuntimeException(
                    "Locator not found for key: " +
                            key
            );
        }

        String[] locatorParts =
                locatorValue.split("=", 2);

        if (locatorParts.length != 2) {

            throw new RuntimeException(
                    "Invalid locator format for key: " +
                            key
            );
        }

        String locatorType =
                locatorParts[0].trim();

        String locator =
                locatorParts[1].trim();

        switch (locatorType.toLowerCase()) {

            case "xpath":
                return By.xpath(locator);

            case "id":
                return By.id(locator);

            case "name":
                return By.name(locator);

            case "css":
            case "cssselector":
                return By.cssSelector(locator);

            case "classname":
                return By.className(locator);

            case "tagname":
                return By.tagName(locator);

            case "linktext":
                return By.linkText(locator);

            case "partiallinktext":
                return By.partialLinkText(locator);

            default:

                throw new RuntimeException(
                        "Unsupported locator type: " +
                                locatorType
                );
        }
    }
}