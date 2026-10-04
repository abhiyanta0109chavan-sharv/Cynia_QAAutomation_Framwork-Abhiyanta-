package com.cynia.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // =========================
    // Locators
    // =========================

    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;


    // =========================
    // Constructor
    // =========================

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        PageFactory.initElements(driver, this);
    }


    // =========================
    // Username
    // =========================

    public void enterUsername(String usernameValue) {

        wait.until(
                ExpectedConditions.visibilityOf(username)
        );

        username.clear();
        username.sendKeys(usernameValue);
    }


    // =========================
    // Password
    // =========================

    public void enterPassword(String passwordValue) {

        wait.until(
                ExpectedConditions.visibilityOf(password)
        );

        password.clear();
        password.sendKeys(passwordValue);
    }


    // =========================
    // Sign In
    // =========================

    public void clickSignIn() {

        wait.until(
                ExpectedConditions.elementToBeClickable(signInButton)
        );

        signInButton.click();
    }


    // =========================
    // Verify Sign In disabled
    // =========================

    public boolean isSignInButtonDisabled() {

        wait.until(
                ExpectedConditions.visibilityOf(signInButton)
        );

        return !signInButton.isEnabled();
    }


    // =========================
    // Invalid Credentials
    // =========================

    public String getInvalidCredentialsMessage() {

        By invalidCredentialsMessage =
                By.xpath(
                        "//*[normalize-space()='Invalid user credentials']"
                );

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        invalidCredentialsMessage
                )
        );

        return message.getText().trim();
    }


    // =========================
    // Successful Login
    // =========================

    public boolean isLoginSuccessful() {

        // Wait until login URL is left
        wait.until(driver ->
                !driver.getCurrentUrl().contains("/login")
        );

        System.out.println(
                "Post-login URL: " +
                        driver.getCurrentUrl()
        );

        return !driver.getCurrentUrl().contains("/login");
    }
}