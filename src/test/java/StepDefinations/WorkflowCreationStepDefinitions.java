package StepDefinations;

import com.cynia.automation.driver.driverfactory;
import com.cynia.automation.pages.WorkflowTemplatePage;
import com.cynia.automation.utils.LocatorUtils;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WorkflowCreationStepDefinitions {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WorkflowTemplatePage workflowTemplatePage;

    private String uniqueWorkflowKey;

    private static final String EXPECTED_WORKFLOW_NAME =
            "ITSM Store Printer Invoice Remediation";

    private final By createdWorkflowTitle =
            LocatorUtils.getLocator(
                    "workflow.created.title"
            );

    private final By createdWorkflowStatus =
            LocatorUtils.getLocator(
                    "workflow.created.status"
            );

    private final By backToWorkflows =
            LocatorUtils.getLocator(
                    "workflow.back.to.workflows"
            );

    private final By createdWorkflowCard =
            LocatorUtils.getLocator(
                    "workflow.created.card"
            );

    private final By createdWorkflowCardName =
            LocatorUtils.getLocator(
                    "workflow.created.card.name"
            );

    private final By createdWorkflowCardStatus =
            LocatorUtils.getLocator(
                    "workflow.created.card.status"
            );

    private final By workflowSearch =
            LocatorUtils.getLocator(
                    "workflow.search"
            );

    public WorkflowCreationStepDefinitions() {

        this.driver = driverfactory.getDriver();

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        this.workflowTemplatePage =
                new WorkflowTemplatePage(driver);
    }


    // =========================================================
    // Create from Starter
    // =========================================================

    @When("the user clicks Create from starter")
    public void theUserClicksCreateFromStarter() {

        workflowTemplatePage.clickCreateFromStarter();
    }


    // =========================================================
    // Template Selection
    // =========================================================

    @When("the user selects the Store Printer Invoice Remediation template")
    public void theUserSelectsTheStorePrinterInvoiceRemediationTemplate() {

        workflowTemplatePage
                .selectStorePrinterInvoiceRemediation();
    }

    @Then("the Store Printer Invoice Remediation template should be selected")
    public void theStorePrinterInvoiceRemediationTemplateShouldBeSelected() {

        WebElement template =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                LocatorUtils.getLocator(
                                        "workflow.template.store.printer.invoice"
                                )
                        )
                );

        Assert.assertTrue(
                template.isDisplayed(),
                "Store Printer Invoice Remediation template is not displayed"
        );
    }


    // =========================================================
    // Next Button
    // =========================================================

    @When("the user clicks Next")
    public void theUserClicksNext() {

        workflowTemplatePage.clickNext();
    }


    // =========================================================
    // Workflow Key
    // =========================================================

    @When("the user updates the workflow key")
    public void theUserUpdatesTheWorkflowKey() {

        uniqueWorkflowKey =
                "test" + System.currentTimeMillis();

        workflowTemplatePage.updateWorkflowKey(
                uniqueWorkflowKey
        );
    }


    // =========================================================
    // Skip Identity & Create
    // =========================================================

    @When("the user skips identity and creates the workflow")
    public void theUserSkipsIdentityAndCreatesTheWorkflow() {

        workflowTemplatePage.clickSkipAndCreate();
    }


    // =========================================================
    // Template Selection Page Verification
    // =========================================================

    @Then("the workflow template selection page should be displayed")
    public void theWorkflowTemplateSelectionPageShouldBeDisplayed() {

        WebElement template =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                LocatorUtils.getLocator(
                                        "workflow.template.store.printer.invoice"
                                )
                        )
                );

        Assert.assertTrue(
                template.isDisplayed(),
                "Workflow template selection page is not displayed"
        );
    }


    // =========================================================
    // Configure Dialog Verification
    // =========================================================

    @Then("the configure workflow dialog should be displayed")
    public void theConfigureWorkflowDialogShouldBeDisplayed() {

        Assert.assertTrue(
                workflowTemplatePage.isConfigureDialogDisplayed(),
                "Configure workflow dialog is not displayed"
        );
    }


    @Then("the configure workflow dialog should not be displayed")
    public void theConfigureWorkflowDialogShouldNotBeDisplayed() {

        Assert.assertFalse(
                workflowTemplatePage.isConfigureDialogDisplayed(),
                "Configure workflow dialog should not be displayed at this stage"
        );
    }


    // =========================================================
    // Risk Threshold
    // =========================================================

    @When("the user sets risk threshold to {string}")
    public void theUserSetsRiskThresholdTo(
            String riskThreshold
    ) {

        workflowTemplatePage.updateRiskThreshold(
                riskThreshold
        );
    }

    @Then("the risk threshold should be {string}")
    public void theRiskThresholdShouldBe(
            String expectedRiskThreshold
    ) {

        String actualRiskThreshold =
                workflowTemplatePage.getRiskThresholdValue();

        Assert.assertEquals(
                actualRiskThreshold,
                expectedRiskThreshold,
                "Risk threshold value is incorrect"
        );
    }


    // =========================================================
    // Save Workflow
    // =========================================================

    @When("the user clicks Save knobs")
    public void theUserClicksSaveKnobs() {

        workflowTemplatePage.clickSaveKnobs();
    }


    // =========================================================
    // Verify Workflow Created Successfully
    // =========================================================

    @Then("the workflow should be created successfully")
    public void theWorkflowShouldBeCreatedSuccessfully() {

        WebElement workflowTitle =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                createdWorkflowTitle
                        )
                );

        Assert.assertTrue(
                workflowTitle.isDisplayed(),
                "Created workflow title is not displayed"
        );

        WebElement workflowStatus =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                createdWorkflowStatus
                        )
                );

        Assert.assertTrue(
                workflowStatus
                        .getText()
                        .trim()
                        .equalsIgnoreCase("Draft"),
                "Created workflow status is not Draft. Actual status: "
                        + workflowStatus.getText().trim()
        );
    }


    // =========================================================
    // Navigate Back to Workflows
    // =========================================================

    @When("the user navigates back to Workflows")
    public void theUserNavigatesBackToWorkflows() {

        WebElement workflowsLink =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                backToWorkflows
                        )
                );

        try {

            workflowsLink.click();

        } catch (Exception e) {

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].click();",
                    workflowsLink
            );
        }

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LocatorUtils.getLocator(
                                "workflow.page.title"
                        )
                )
        );
    }


    // =========================================================
    // Search Newly Created Workflow
    // =========================================================

    @When("the user searches for the newly created workflow")
    public void theUserSearchesForTheNewlyCreatedWorkflow() {

        Assert.assertNotNull(
                uniqueWorkflowKey,
                "Workflow key was not generated"
        );

        WebElement searchBox =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                workflowSearch
                        )
                );

        searchBox.clear();

        searchBox.sendKeys(
                uniqueWorkflowKey
        );
    }


    // =========================================================
    // Verify Newly Created Workflow in List
    // =========================================================

    @Then("the newly created workflow should appear in the workflow list")
    public void theNewlyCreatedWorkflowShouldAppearInTheWorkflowList() {

        List<WebElement> workflowCards =
                wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                createdWorkflowCard
                        )
                );

        Assert.assertEquals(
                workflowCards.size(),
                1,
                "Expected exactly one workflow card but found: "
                        + workflowCards.size()
        );

        WebElement workflowCard =
                workflowCards.get(0);

        WebElement workflowName =
                workflowCard.findElement(
                        createdWorkflowCardName
                );

        Assert.assertTrue(
                workflowName.isDisplayed(),
                "Newly created workflow is not displayed in workflow list"
        );

        Assert.assertEquals(
                workflowName.getText().trim(),
                EXPECTED_WORKFLOW_NAME,
                "Workflow name is incorrect"
        );

        WebElement workflowStatus =
                workflowCard.findElement(
                        createdWorkflowCardStatus
                );

        Assert.assertTrue(
                workflowStatus
                        .getText()
                        .trim()
                        .equalsIgnoreCase("Draft"),
                "Workflow status is not Draft. Actual status: "
                        + workflowStatus.getText().trim()
        );
    }
}
