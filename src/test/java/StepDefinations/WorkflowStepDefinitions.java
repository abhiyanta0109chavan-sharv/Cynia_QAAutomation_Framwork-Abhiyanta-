package StepDefinations;

import com.cynia.automation.driver.driverfactory;
import com.cynia.automation.pages.LoginPage;
import com.cynia.automation.pages.WorkflowPage;
import com.cynia.automation.utils.ExcelUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class WorkflowStepDefinitions {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final WorkflowPage workflowPage;

    public WorkflowStepDefinitions() {

        driver =
                driverfactory.getDriver();

        loginPage =
                new LoginPage(driver);

        workflowPage =
                new WorkflowPage(driver);

        Reporter.log("WorkflowStepDefinitions initialized successfully", true);
    }

    @Given("user is logged into the Cynia application")
    public void userIsLoggedIntoTheCyniaApplication() {

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

        loginPage.clickSignIn();

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "User could not log in successfully"
        );

        Reporter.log("User logged into the Cynia application successfully", true);
    }

    @Given("user navigates to the Workflows screen")
    public void userNavigatesToTheWorkflowsScreen() {

        workflowPage.clickWorkflowsMenu();

        Reporter.log("User navigated to the Workflows screen", true);
    }

    @Then("the Workflows screen should be displayed")
    public void theWorkflowsScreenShouldBeDisplayed() {

        Assert.assertTrue(
                workflowPage.isWorkflowPageDisplayed(),
                "Workflows screen is not displayed"
        );

        Reporter.log("Workflows screen is displayed", true);
    }

    @Then("the workflow list should be displayed")
    public void theWorkflowListShouldBeDisplayed() {

        Assert.assertTrue(
                workflowPage.areWorkflowsDisplayed(),
                "Workflow list is not displayed"
        );

        Reporter.log("Workflow list is displayed", true);
    }

    @Then("workflow name and workflow status should be displayed")
    public void workflowNameAndWorkflowStatusShouldBeDisplayed() {

        String workflowName =
                workflowPage.getWorkflowName();

        String workflowStatus =
                workflowPage.getWorkflowStatus();

        Assert.assertFalse(
                workflowName.trim().isEmpty(),
                "Workflow name is not displayed"
        );

        Assert.assertFalse(
                workflowStatus.trim().isEmpty(),
                "Workflow status is not displayed"
        );

        Reporter.log(
                "Workflow name and status displayed successfully. Name: "
                        + workflowName
                        + ", Status: "
                        + workflowStatus,
                true
        );
    }

    @Then("available actions for the workflow should be displayed")
    public void availableActionsForTheWorkflowShouldBeDisplayed() {

        Assert.assertTrue(
                workflowPage.areWorkflowActionsDisplayed(),
                "Workflow actions are not displayed"
        );

        Reporter.log("Available workflow actions are displayed", true);
    }

    @When("user searches for a workflow")
    public void userSearchesForAWorkflow() {

        workflowPage.searchWorkflow("test1");

        Reporter.log("Workflow search performed for: test1", true);
    }

    @Then("the matching workflow should be displayed")
    public void theMatchingWorkflowShouldBeDisplayed() {

        String workflowName =
                workflowPage.getWorkflowName();

        Assert.assertEquals(
                workflowName,
                "test1",
                "Expected workflow 'test1' was not displayed"
        );

        Reporter.log(
                "Matching workflow displayed: " + workflowName,
                true
        );
    }

    @Then("the search filter should be displayed")
    public void theSearchFilterShouldBeDisplayed() {

        Assert.assertTrue(
                workflowPage.isSearchFilterDisplayed(
                        "test1"
                ),
                "Search filter 'test1' is not displayed"
        );

        Reporter.log("Search filter 'test1' is displayed", true);
    }

    @When("user removes the search filter")
    public void userRemovesTheSearchFilter() {

        workflowPage.removeIndividualFilter();

        Reporter.log("Individual search filter removed", true);
    }

    @Then("the search filter should be removed")
    public void theSearchFilterShouldBeRemoved() {

        Assert.assertFalse(
                workflowPage.isSearchFilterDisplayed(
                        "test1"
                ),
                "Search filter 'test1' was not removed"
        );

        Reporter.log("Search filter 'test1' was removed successfully", true);
    }

    @When("user clicks Clear filters")
    public void userClicksClearFilters() {

        workflowPage.clickClearFilters();

        Reporter.log("Clear filters button clicked", true);
    }

    @Then("all active filters should be cleared")
    public void allActiveFiltersShouldBeCleared() {

        Assert.assertFalse(
                workflowPage.isActiveFiltersSectionDisplayed(),
                "Active filters were not cleared"
        );

        Reporter.log("All active filters were cleared", true);
    }

    @Then("the workflow search box should be empty")
    public void theWorkflowSearchBoxShouldBeEmpty() {

        Assert.assertTrue(
                workflowPage.isSearchBoxEmpty(),
                "Workflow search box is not empty"
        );

        Reporter.log("Workflow search box is empty", true);
    }

    @When("user opens a workflow")
    public void userOpensAWorkflow() {

        workflowPage.openWorkflow();

        Reporter.log("Workflow opened successfully", true);
    }

    @Then("the workflow details screen should be displayed")
    public void theWorkflowDetailsScreenShouldBeDisplayed() {

        Assert.assertTrue(
                workflowPage.isDetailsPageDisplayed(),
                "Workflow details screen is not displayed"
        );

        Reporter.log("Workflow details screen is displayed", true);
    }
}