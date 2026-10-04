package com.cynia.automation.pages;

import com.cynia.automation.driver.driverfactory;
import com.cynia.automation.utils.LocatorUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class WorkflowExecutionPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WorkflowExecutionPage() {
        driver = driverfactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isWorkflowsHeadingDisplayed() {

        By locator =
                LocatorUtils.getLocator("flow4.workflows.heading");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }

    public void openStorePrinterInvoiceWorkflow() {

        By locator =
                LocatorUtils.getLocator("flow4.workflow.card");

        WebElement workflow = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        workflow.click();
    }

    public boolean isStorePrinterInvoiceWorkflowHeadingDisplayed() {

        By locator =
                LocatorUtils.getLocator("flow4.workflow.heading");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }

    public void openWorkflowBuilder() {

        By builderLocator =
                LocatorUtils.getLocator("flow4.builder");

        WebElement builder = wait.until(
                ExpectedConditions.elementToBeClickable(builderLocator)
        );

        builder.click();

        Reporter.log(
                "Workflow builder opened.",
                true
        );
    }

    public boolean isTestDraftWorkflowButtonDisplayed() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.test.draft.workflow.button"
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }

    public void assignQueueIfRequired() {

        By assignQueueLocator =
                LocatorUtils.getLocator("flow4.assign.queue");

        boolean queueAssignmentRequired = false;

        try {

            WebDriverWait queueWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(5)
                    );

            WebElement assignQueueButton = queueWait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            assignQueueLocator
                    )
            );

            if (assignQueueButton.isDisplayed()
                    && assignQueueButton.isEnabled()) {

                queueAssignmentRequired = true;
            }

        } catch (TimeoutException e) {

            queueAssignmentRequired = false;
        }

        if (queueAssignmentRequired) {

            Reporter.log(
                    "Queue is NOT assigned. Assigning queue...",
                    true
            );

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            assignQueueLocator
                    )
            ).click();

            Reporter.log(
                    "Assign queue clicked",
                    true
            );

            By queueDropdownLocator =
                    LocatorUtils.getLocator(
                            "flow4.queue.dropdown"
                    );

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            queueDropdownLocator
                    )
            ).click();

            Reporter.log(
                    "Workflow Queue dropdown opened",
                    true
            );

            By devQueueLocator =
                    LocatorUtils.getLocator(
                            "flow4.dev.queue"
                    );

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            devQueueLocator
                    )
            ).click();

            Reporter.log(
                    "dev-playground-queue selected",
                    true
            );

            By saveQueueLocator =
                    LocatorUtils.getLocator(
                            "flow4.save.queue"
                    );

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            saveQueueLocator
                    )
            ).click();

            Reporter.log(
                    "Queue settings save clicked",
                    true
            );

        } else {

            Reporter.log(
                    "Queue is already assigned. Skipping Assign Queue.",
                    true
            );
        }
    }

    public boolean isQueueAssignmentCompleted() {

        By queueHeadingLocator =
                LocatorUtils.getLocator(
                        "flow4.queue.heading"
                );

        try {

            WebDriverWait queueWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(20)
                    );

            queueWait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            queueHeadingLocator
                    )
            );

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }

    /*
     * Main method used by StepDefinition.
     *
     * Handles both application paths:
     *
     * PATH 1:
     * Test draft workflow -> Assign Queue -> Save
     * -> builder -> Test draft workflow AGAIN
     * -> Test draft dialog
     *
     * PATH 2:
     * Test draft workflow -> Test draft dialog directly
     */
    public void clickTestDraftWorkflowButton() {

        By testDraftWorkflowButtonLocator =
                LocatorUtils.getLocator(
                        "flow4.test.draft.workflow.button"
                );

        By queueHeadingLocator =
                LocatorUtils.getLocator(
                        "flow4.queue.heading"
                );

        By dialogHeadingLocator =
                LocatorUtils.getLocator(
                        "flow4.test.draft.workflow.dialog.heading"
                );

        /*
         * FIRST CLICK
         */
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        testDraftWorkflowButtonLocator
                )
        ).click();

        Reporter.log(
                "Test draft workflow button clicked.",
                true
        );

        /*
         * Wait for either:
         *
         * 1. Assign Queue popup
         * OR
         * 2. Test draft workflow dialog
         *
         * Maximum wait = 60 seconds.
         */
        Reporter.log(
                "Waiting for Assign Queue popup or Test draft workflow dialog...",
                true
        );

        waitForQueueOrTestDraftDialog(
                queueHeadingLocator,
                dialogHeadingLocator
        );

        /*
         * Check whether Assign Queue is displayed.
         */
        if (isElementDisplayed(queueHeadingLocator)) {

            Reporter.log(
                    "Assign Queue popup detected.",
                    true
            );

            handleAssignQueue();

            /*
             * Queue was assigned.
             *
             * Application returns to builder.
             * Therefore Test draft workflow must be clicked AGAIN.
             */
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            testDraftWorkflowButtonLocator
                    )
            ).click();

            Reporter.log(
                    "Test draft workflow button clicked again after queue assignment.",
                    true
            );

            /*
             * Now wait specifically for Test draft workflow dialog.
             */
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            dialogHeadingLocator
                    )
            );

            Reporter.log(
                    "Test draft workflow dialog is ready after queue assignment.",
                    true
            );

            return;
        }

        /*
         * Test Draft dialog appeared directly.
         *
         * Important:
         * The application can still open Assign Queue slightly later.
         *
         * Give the application a short additional monitoring period
         * before allowing JSON entry.
         */
        Reporter.log(
                "Test draft workflow dialog detected directly.",
                true
        );

        Reporter.log(
                "Monitoring briefly for late Assign Queue popup...",
                true
        );

        boolean lateQueueDisplayed =
                waitForLateQueuePopup(
                        queueHeadingLocator,
                        10
                );

        if (lateQueueDisplayed) {

            Reporter.log(
                    "Late Assign Queue popup detected.",
                    true
            );

            handleAssignQueue();

            /*
             * After queue assignment application returns to builder.
             * Click Test draft workflow again.
             */
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            testDraftWorkflowButtonLocator
                    )
            ).click();

            Reporter.log(
                    "Test draft workflow button clicked again after late queue assignment.",
                    true
            );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            dialogHeadingLocator
                    )
            );

            Reporter.log(
                    "Test draft workflow dialog is ready.",
                    true
            );

        } else {

            Reporter.log(
                    "No late Assign Queue popup detected. Continuing to JSON.",
                    true
            );
        }
    }

    /*
     * Wait until either Queue popup or Test Draft dialog appears.
     */
    private void waitForQueueOrTestDraftDialog(
            By queueHeadingLocator,
            By dialogHeadingLocator) {

        new WebDriverWait(
                driver,
                Duration.ofSeconds(60)
        ).until(driver -> {

            if (isElementDisplayed(queueHeadingLocator)) {
                return true;
            }

            if (isElementDisplayed(dialogHeadingLocator)) {
                return true;
            }

            return false;
        });
    }

    /*
     * Monitor for a late Assign Queue popup.
     *
     * Returns:
     * true  -> Queue appeared
     * false -> Queue did not appear
     */
    private boolean waitForLateQueuePopup(
            By queueHeadingLocator,
            int seconds) {

        try {

            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(seconds)
            ).until(driver ->
                    isElementDisplayed(queueHeadingLocator)
            );

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }

    /*
     * Actual queue handling.
     */
    private void handleAssignQueue() {

        assignQueueIfRequired();

        Reporter.log(
                "Assign Queue handling completed.",
                true
        );

        /*
         * Wait until Assign Queue popup disappears.
         */
        boolean queueClosed =
                isQueueAssignmentCompleted();

        if (queueClosed) {

            Reporter.log(
                    "Assign Queue popup closed.",
                    true
            );

        } else {

            Reporter.log(
                    "Assign Queue popup close wait timed out. Continuing...",
                    true
            );
        }
    }

    /*
     * Safe visibility check.
     *
     * findElements() avoids throwing NoSuchElementException.
     */
    private boolean isElementDisplayed(By locator) {

        try {

            List<WebElement> elements =
                    driver.findElements(locator);

            for (WebElement element : elements) {

                try {

                    if (element.isDisplayed()) {
                        return true;
                    }

                } catch (StaleElementReferenceException ignored) {
                }
            }

        } catch (WebDriverException ignored) {
        }

        return false;
    }

    public boolean isTestDraftWorkflowDialogDisplayed() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.test.draft.workflow.dialog.heading"
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }

    public void enterInputDataJson() {

        By queueHeadingLocator =
                LocatorUtils.getLocator(
                        "flow4.queue.heading"
                );

        By testDraftWorkflowButtonLocator =
                LocatorUtils.getLocator(
                        "flow4.test.draft.workflow.button"
                );

        By dialogHeadingLocator =
                LocatorUtils.getLocator(
                        "flow4.test.draft.workflow.dialog.heading"
                );

        /*
         * FINAL SAFETY CHECK:
         *
         * Before searching for JSON, check once more whether
         * Assign Queue appeared late.
         */
        if (isElementDisplayed(queueHeadingLocator)) {

            Reporter.log(
                    "Assign Queue popup detected before JSON entry.",
                    true
            );

            handleAssignQueue();

            /*
             * Application returned to builder.
             * Open Test draft workflow again.
             */
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            testDraftWorkflowButtonLocator
                    )
            ).click();

            Reporter.log(
                    "Test draft workflow button clicked again before JSON entry.",
                    true
            );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            dialogHeadingLocator
                    )
            );

            Reporter.log(
                    "Test draft workflow dialog reopened for JSON entry.",
                    true
            );
        }

        /*
         * Now locate the confirmed JSON textarea.
         */
        By jsonLocator =
                LocatorUtils.getLocator(
                        "flow4.input.data.json"
                );

        WebDriverWait jsonWait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(120)
                );

        WebElement jsonField = jsonWait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        jsonLocator
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                jsonField
        );

        jsonField.click();

        jsonField.sendKeys(Keys.CONTROL + "a");

        jsonField.sendKeys(Keys.BACK_SPACE);

        String jsonData =
                "{\n"
                        + "  \"description\":\"Printer invoice remediation\",\n"
                        + "  \"external_id\":\"EXT-001\",\n"
                        + "  \"priority\":\"1\",\n"
                        + "  \"source_system\":\"ITSM\",\n"
                        + "  \"ticket_number\":\"INC-001\"\n"
                        + "}";

        jsonField.sendKeys(jsonData);

        Reporter.log(
                "Valid inputData JSON entered.",
                true
        );
    }

    public boolean isTestDraftButtonEnabled() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.test.draft.button"
                );

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        return button.isEnabled();
    }

    public void clickTestDraftButton() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.test.draft.button"
                );

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        button.click();

        Reporter.log(
                "Test draft button clicked.",
                true
        );
    }

    public boolean isExecutionTraceDisplayed() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.execution.trace"
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }

    public boolean isTracesHeadingDisplayed() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.traces.heading"
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }

    public String getOverallExecutionStatus() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.execution.overall.status"
                );

        WebElement status = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        return status.getText().trim();
    }

    public List<String> getDisplayedTraceStatuses() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.trace.statuses"
                );

        List<WebElement> elements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        locator
                )
        );

        Set<String> uniqueStatuses =
                new LinkedHashSet<>();

        for (WebElement element : elements) {

            String text =
                    element.getText().trim();

            if (!text.isEmpty()) {
                uniqueStatuses.add(text);
            }
        }

        return new ArrayList<>(uniqueStatuses);
    }

    public void printHumanResponseLimitation() {

        System.out.println(
                "INFO - Human response is not automated because "
                        + "the Human Response UI is currently not working."
        );

        System.out.println(
                "INFO - Continuing with navigation and execution "
                        + "status verification."
        );
    }

    public void navigateBackToWorkflowOverview() {

        By closeButtonLocator =
                LocatorUtils.getLocator(
                        "flow4.trace.close.button"
                );

        WebElement closeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        closeButtonLocator
                )
        );

        closeButton.click();

        By backButtonLocator =
                LocatorUtils.getLocator(
                        "flow4.builder.back.button"
                );

        WebElement backButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        backButtonLocator
                )
        );

        backButton.click();
    }

    public boolean isWorkflowOverviewDisplayed() {

        By locator =
                LocatorUtils.getLocator(
                        "flow4.workflow.heading"
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }
}