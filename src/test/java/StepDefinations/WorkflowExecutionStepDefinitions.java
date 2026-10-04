package StepDefinations;

import com.cynia.automation.pages.WorkflowExecutionPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class WorkflowExecutionStepDefinitions {

    private final WorkflowExecutionPage workflowExecutionPage;

    public WorkflowExecutionStepDefinitions() {

        workflowExecutionPage =
                new WorkflowExecutionPage();

        Reporter.log(
                "WorkflowExecutionStepDefinitions initialized successfully",
                true
        );
    }

    // ============================================================
    // SCENARIO 1
    // ============================================================

    @Then("the Workflows heading should be displayed")
    public void theWorkflowsHeadingShouldBeDisplayed() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isWorkflowsHeadingDisplayed(),

                "Workflows heading is not displayed"
        );

        Reporter.log(
                "Workflows heading is displayed",
                true
        );
    }


    // ============================================================
    // SCENARIO 2
    // ============================================================

    @When("user opens the ITSM Store Printer Invoice Remediation workflow")
    public void userOpensTheStorePrinterInvoiceRemediationWorkflow() {

        workflowExecutionPage
                .openStorePrinterInvoiceWorkflow();

        Reporter.log(
                "ITSM Store Printer Invoice Remediation workflow opened",
                true
        );
    }


    @Then("the ITSM Store Printer Invoice Remediation heading should be displayed")
    public void theITSMStorePrinterInvoiceRemediationHeadingShouldBeDisplayed() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isStorePrinterInvoiceWorkflowHeadingDisplayed(),

                "ITSM Store Printer Invoice Remediation heading is not displayed"
        );

        Reporter.log(
                "ITSM Store Printer Invoice Remediation heading is displayed",
                true
        );
    }


    // ============================================================
    // SCENARIO 3
    // ============================================================

    @When("user opens the workflow builder")
    public void userOpensTheWorkflowBuilder() {

        workflowExecutionPage
                .openWorkflowBuilder();

        Reporter.log(
                "Workflow builder opened",
                true
        );
    }


    @Then("the Test draft workflow button should be displayed")
    public void theTestDraftWorkflowButtonShouldBeDisplayed() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isTestDraftWorkflowButtonDisplayed(),

                "Test draft workflow button is not displayed"
        );

        Reporter.log(
                "Test draft workflow button is displayed",
                true
        );
    }


    // ============================================================
    // ASSIGN QUEUE - ACTION
    //
    // Existing step kept.
    // If Assign Queue popup is already present, it will be handled.
    // If it is not present, it will simply be skipped.
    // ============================================================

    @When("user assigns the required queue if needed")
    public void userAssignsTheRequiredQueueIfNeeded() {

        workflowExecutionPage
                .assignQueueIfRequired();

        Reporter.log(
                "Assign queue action completed or skipped if queue was already assigned",
                true
        );
    }


    // ============================================================
    // ASSIGN QUEUE - VERIFICATION
    // ============================================================

    @Then("the queue assignment should be completed")
    public void theQueueAssignmentShouldBeCompleted() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isQueueAssignmentCompleted(),

                "Queue assignment was not completed successfully"
        );

        Reporter.log(
                "Queue assignment verified successfully",
                true
        );
    }


    // ============================================================
    // SCENARIO 4
    // TEST DRAFT WORKFLOW
    // ============================================================

    @When("user clicks the Test draft workflow button")
    public void userClicksTheTestDraftWorkflowButton() {

        /*
         * Page method now does:
         *
         * Click Test draft workflow
         *          ↓
         * Detect Assign Queue OR Test Draft
         *          ↓
         * If Queue -> assign + Save
         *          ↓
         * Wait for Queue close
         *          ↓
         * Wait for Test Draft dialog
         */
        workflowExecutionPage
                .clickTestDraftWorkflowButton();

        Reporter.log(
                "Test draft workflow popup sequencing completed",
                true
        );
    }


    @Then("the Test draft workflow dialog should be displayed")
    public void theTestDraftWorkflowDialogShouldBeDisplayed() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isTestDraftWorkflowDialogDisplayed(),

                "Test draft workflow dialog is not displayed"
        );

        Reporter.log(
                "Test draft workflow dialog is displayed",
                true
        );
    }


    // ============================================================
    // SCENARIO 5
    // INPUT DATA JSON
    // ============================================================

    @When("user enters valid inputData JSON")
    public void userEntersValidInputDataJson() {

        workflowExecutionPage
                .enterInputDataJson();

        Reporter.log(
                "Valid inputData JSON entered",
                true
        );
    }


    @Then("the Test draft button should be enabled")
    public void theTestDraftButtonShouldBeEnabled() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isTestDraftButtonEnabled(),

                "Test draft button is not enabled"
        );

        Reporter.log(
                "Test draft button is enabled",
                true
        );
    }


    @When("user clicks the Test draft button")
    public void userClicksTheTestDraftButton() {

        workflowExecutionPage
                .clickTestDraftButton();

        Reporter.log(
                "Test draft button clicked",
                true
        );
    }


    @Then("the execution trace should be displayed")
    public void theExecutionTraceShouldBeDisplayed() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isExecutionTraceDisplayed(),

                "Execution trace is not displayed"
        );

        Reporter.log(
                "Execution trace is displayed",
                true
        );
    }


    // ============================================================
    // SCENARIO 6
    // ============================================================

    @Then("the Traces heading should be displayed")
    public void theTracesHeadingShouldBeDisplayed() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isTracesHeadingDisplayed(),

                "Traces heading is not displayed"
        );

        Reporter.log(
                "Traces heading is displayed",
                true
        );
    }


    @And("the current workflow execution status should be displayed")
    public void theCurrentWorkflowExecutionStatusShouldBeDisplayed() {

        String overallStatus =
                workflowExecutionPage
                        .getOverallExecutionStatus();

        Assert.assertFalse(
                overallStatus.isEmpty(),

                "Workflow execution status is empty"
        );

        System.out.println(
                "========== WORKFLOW EXECUTION STATUS =========="
        );

        System.out.println(
                "Overall execution status: "
                        + overallStatus
        );

        System.out.println(
                "==============================================="
        );

        Reporter.log(
                "Overall workflow execution status: "
                        + overallStatus,
                true
        );
    }


    @And("the trace statuses should be displayed")
    public void theTraceStatusesShouldBeDisplayed() {

        List<String> statuses =
                workflowExecutionPage
                        .getDisplayedTraceStatuses();

        Assert.assertFalse(
                statuses.isEmpty(),

                "No trace statuses were displayed"
        );

        System.out.println(
                "========== TRACE STATUSES =========="
        );

        for (String status : statuses) {

            System.out.println(
                    "Trace status: " + status
            );
        }

        System.out.println(
                "===================================="
        );

        Reporter.log(
                "Trace statuses displayed. Total trace statuses: "
                        + statuses.size(),
                true
        );
    }


    @And("the human response limitation should be reported")
    public void theHumanResponseLimitationShouldBeReported() {

        workflowExecutionPage
                .printHumanResponseLimitation();

        Reporter.log(
                "Human response limitation reported",
                true
        );
    }


    @When("user navigates back to the workflow overview")
    public void userNavigatesBackToTheWorkflowOverview() {

        workflowExecutionPage
                .navigateBackToWorkflowOverview();

        Reporter.log(
                "Navigated back to workflow overview",
                true
        );
    }


    @Then("the workflow overview should be displayed")
    public void theWorkflowOverviewShouldBeDisplayed() {

        Assert.assertTrue(
                workflowExecutionPage
                        .isWorkflowOverviewDisplayed(),

                "Workflow overview is not displayed after navigation back"
        );

        Reporter.log(
                "Workflow overview is displayed after navigation back",
                true
        );
    }
}