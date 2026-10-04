package StepDefinations;

import com.cynia.automation.driver.driverfactory;
import com.cynia.automation.pages.LoginPage;
import com.cynia.automation.utils.ExcelUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginStepDefinitions {

    private final WebDriver driver;
    private final LoginPage loginPage;


    // =========================
    // Constructor
    // =========================

    public LoginStepDefinitions() {

        driver = driverfactory.getDriver();
        loginPage = new LoginPage(driver);

        Reporter.log("LoginStepDefinitions initialized successfully", true);
    }


    // =========================
    // Given
    // =========================

    @Given("the user is on the Cynia login page")
    public void theUserIsOnTheCyniaLoginPage() {

        // Browser and URL are handled by Hooks
        Reporter.log("User is on the Cynia login page", true);
    }


    // =========================
    // Valid credentials
    // =========================

    @When("the user enters valid username and password")
    public void theUserEntersValidUsernameAndPassword() {

        String username =
                ExcelUtils.getCellData(
                        "LoginTestData",
                        "ValidLogin",
                        "Username"
                );

        String password =
                ExcelUtils.getCellData(
                        "LoginTestData",
                        "ValidLogin",
                        "Password"
                );

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        Reporter.log("Valid username and password entered", true);
    }


    // =========================
    // Invalid credentials
    // =========================

    @When("the user enters invalid username and password")
    public void theUserEntersInvalidUsernameAndPassword() {

        String username =
                ExcelUtils.getCellData(
                        "LoginTestData",
                        "InvalidLogin",
                        "Username"
                );

        String password =
                ExcelUtils.getCellData(
                        "LoginTestData",
                        "InvalidLogin",
                        "Password"
                );

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        Reporter.log("Invalid username and password entered", true);
    }


    // =========================
    // Invalid username + valid password
    // =========================

    @When("the user enters invalid username and valid password")
    public void theUserEntersInvalidUsernameAndValidPassword() {

        String username =
                ExcelUtils.getCellData(
                        "LoginTestData",
                        "InvalidUsernameValidPassword",
                        "Username"
                );

        String password =
                ExcelUtils.getCellData(
                        "LoginTestData",
                        "InvalidUsernameValidPassword",
                        "Password"
                );

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        Reporter.log("Invalid username and valid password entered", true);
    }


    // =========================
    // Blank credentials
    // =========================

    @When("the user leaves the username and password fields blank")
    public void theUserLeavesTheUsernameAndPasswordFieldsBlank() {

        // Username and password are intentionally left blank
        Reporter.log("Username and password fields left blank", true);
    }


    // =========================
    // Click Sign In
    // =========================

    @When("the user clicks on the Sign In button")
    public void theUserClicksOnTheSignInButton() {

        loginPage.clickSignIn();

        Reporter.log("Sign In button clicked", true);
    }


    // =========================
    // Successful login
    // =========================

    @Then("the user should be successfully logged in")
    public void theUserShouldBeSuccessfullyLoggedIn() {

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "User was not redirected after successful login"
        );

        Reporter.log("User successfully logged in and redirected", true);
    }


    // =========================
    // Invalid credentials message
    // =========================

    @Then("the appropriate login error message should be displayed")
    public void theAppropriateLoginErrorMessageShouldBeDisplayed() {

        String actualMessage =
                loginPage.getInvalidCredentialsMessage();

        Assert.assertEquals(
                actualMessage,
                "Invalid user credentials",
                "Incorrect login error message"
        );

        Reporter.log("Correct login error message displayed: " + actualMessage, true);
    }


    // =========================
    // Sign In disabled
    // =========================

    @Then("the Sign In button should be disabled")
    public void theSignInButtonShouldBeDisabled() {

        Assert.assertTrue(
                loginPage.isSignInButtonDisabled(),
                "Sign In button should be disabled when username and password are blank"
        );

        Reporter.log("Sign In button is disabled when credentials are blank", true);
    }
}