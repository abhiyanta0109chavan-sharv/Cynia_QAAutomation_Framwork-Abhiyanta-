package com.cynia.automation.pages;

import com.cynia.automation.utils.LocatorUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WorkflowTemplatePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // =========================
    // Create from Starter
    // =========================

    private final By createFromStarterButton =
            LocatorUtils.getLocator(
                    "workflow.create.from.starter"
            );

    // =========================
    // Template Selection
    // =========================

    private final By storePrinterInvoiceTemplate =
            LocatorUtils.getLocator(
                    "workflow.template.store.printer.invoice"
            );

    // =========================
    // Navigation
    // =========================

    private final By nextButton =
            LocatorUtils.getLocator(
                    "workflow.next"
            );

    // =========================
    // Workflow Information
    // =========================

    private final By workflowKeyInput =
            LocatorUtils.getLocator(
                    "workflow.key"
            );

    private final By skipAndCreateButton =
            LocatorUtils.getLocator(
                    "workflow.skip.and.create"
            );

    // =========================
    // Configure Workflow
    // =========================

    private final By configureDialog =
            LocatorUtils.getLocator(
                    "workflow.configure.dialog"
            );

    private final By riskThresholdInput =
            LocatorUtils.getLocator(
                    "workflow.risk.threshold"
            );

    private final By saveKnobsButton =
            LocatorUtils.getLocator(
                    "workflow.save.knobs"
            );

    // =========================
    // Constructor
    // =========================

    public WorkflowTemplatePage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(15)
                );
    }

    // =========================
    // Create from Starter
    // =========================

    public void clickCreateFromStarter() {

        WebElement button =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        createFromStarterButton
                                )
                );

        button.click();
    }

    // =========================
    // Template Selection
    // =========================

    public void selectStorePrinterInvoiceRemediation() {

        WebElement template =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        storePrinterInvoiceTemplate
                                )
                );

        template.click();
    }

    // =========================
    // Navigation
    // =========================

    public void clickNext() {

        WebElement button =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        nextButton
                                )
                );

        button.click();
    }

    // =========================
    // Workflow Information
    // =========================

    public void updateWorkflowKey(
            String workflowKey
    ) {

        WebElement input =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        workflowKeyInput
                                )
                );

        input.clear();

        input.sendKeys(
                workflowKey
        );
    }

    public void clickSkipAndCreate() {

        WebElement button =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        skipAndCreateButton
                                )
                );

        button.click();
    }

    // =========================
    // Configure Workflow
    // =========================

    public boolean isConfigureDialogDisplayed() {

        try {

            WebElement dialog =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            configureDialog
                                    )
                    );

            return dialog.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // =========================
    // Risk Threshold
    // =========================

    public void updateRiskThreshold(
            String riskThreshold
    ) {

        int maxAttempts = 3;

        for (
                int attempt = 1;
                attempt <= maxAttempts;
                attempt++
        ) {

            try {

                WebElement input =
                        wait.until(
                                ExpectedConditions
                                        .visibilityOfElementLocated(
                                                riskThresholdInput
                                        )
                        );

                input.clear();

                input.sendKeys(
                        riskThreshold
                );

                String actualValue =
                        input.getAttribute(
                                "value"
                        );

                if (
                        riskThreshold.equals(
                                actualValue
                        )
                ) {

                    return;
                }

            } catch (
                    StaleElementReferenceException |
                    InvalidElementStateException e
            ) {

                if (
                        attempt == maxAttempts
                ) {

                    throw e;
                }
            }
        }

        throw new IllegalStateException(
                "Risk threshold could not be updated to: "
                        + riskThreshold
        );
    }

    public String getRiskThresholdValue() {

        WebElement input =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        riskThresholdInput
                                )
                );

        return input.getAttribute(
                "value"
        );
    }

    // =========================
    // Save Configuration
    // =========================

    public void clickSaveKnobs() {

        WebElement button =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        saveKnobsButton
                                )
                );

        button.click();
    }
}