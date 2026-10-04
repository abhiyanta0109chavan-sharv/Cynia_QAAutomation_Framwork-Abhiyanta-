package com.cynia.automation.pages;

import com.cynia.automation.utils.LocatorUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WorkflowPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // =========================
    // Workflow screen
    // =========================

    private final By workflowsMenu =
            LocatorUtils.getLocator(
                    "workflow.menu"
            );

    private final By workflowPageHeading =
            LocatorUtils.getLocator(
                    "workflow.page.title"
            );

    // =========================
    // Workflow card
    // =========================

    private final By workflowCards =
            LocatorUtils.getLocator(
                    "workflow.card"
            );

    private final By workflowLink =
            LocatorUtils.getLocator(
                    "workflow.link"
            );

    private final By workflowName =
            LocatorUtils.getLocator(
                    "workflow.name"
            );

    private final By workflowStatus =
            LocatorUtils.getLocator(
                    "workflow.status"
            );

    // =========================
    // Workflow actions
    // =========================

    private final By builderButton =
            LocatorUtils.getLocator(
                    "workflow.builder"
            );

    private final By settingsButton =
            LocatorUtils.getLocator(
                    "workflow.settings"
            );

    private final By editButton =
            LocatorUtils.getLocator(
                    "workflow.edit"
            );

    private final By duplicateButton =
            LocatorUtils.getLocator(
                    "workflow.duplicate"
            );

    private final By deleteButton =
            LocatorUtils.getLocator(
                    "workflow.delete"
            );

    // =========================
    // Search / Filters
    // =========================

    private final By searchBox =
            LocatorUtils.getLocator(
                    "workflow.search"
            );

    private final By activeFiltersSection =
            LocatorUtils.getLocator(
                    "workflow.active.filters"
            );

    private final By clearFiltersButton =
            LocatorUtils.getLocator(
                    "workflow.clear.filters"
            );

    private final By removeSearchFilterButton =
            LocatorUtils.getLocator(
                    "workflow.remove.filter"
            );

    private final By searchFilter =
            LocatorUtils.getLocator(
                    "workflow.search.filter"
            );

    // =========================
    // Constructor
    // =========================

    public WorkflowPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(15)
                );
    }

    // =========================
    // Navigation
    // =========================

    public void clickWorkflowsMenu() {

        WebElement workflows =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        workflowsMenu
                                )
                );

        workflows.click();

        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                workflowPageHeading
                        )
        );
    }

    public boolean isWorkflowPageDisplayed() {

        try {

            WebElement heading =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            workflowPageHeading
                                    )
                    );

            return heading.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // =========================
    // Workflow list
    // =========================

    public boolean areWorkflowsDisplayed() {

        try {

            List<WebElement> cards =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfAllElementsLocatedBy(
                                            workflowCards
                                    )
                    );

            return !cards.isEmpty();

        } catch (Exception e) {

            return false;
        }
    }

    private WebElement getFirstWorkflowCard() {

        List<WebElement> cards =
                wait.until(
                        ExpectedConditions
                                .visibilityOfAllElementsLocatedBy(
                                        workflowCards
                                )
                );

        if (cards.isEmpty()) {

            throw new RuntimeException(
                    "No workflow card was found"
            );
        }

        return cards.get(0);
    }

    // =========================
    // Workflow information
    // =========================

    public String getWorkflowName() {

        WebElement card =
                getFirstWorkflowCard();

        List<WebElement> names =
                card.findElements(
                        workflowName
                );

        if (names.isEmpty()) {

            throw new RuntimeException(
                    "Workflow name was not found"
            );
        }

        return names.get(0)
                .getText()
                .trim();
    }

    public String getWorkflowStatus() {

        WebElement card =
                getFirstWorkflowCard();

        List<WebElement> statuses =
                card.findElements(
                        workflowStatus
                );

        if (statuses.isEmpty()) {

            throw new RuntimeException(
                    "Workflow status was not found"
            );
        }

        return statuses.get(0)
                .getText()
                .trim();
    }

    // =========================
    // Workflow actions
    // =========================

    public boolean areWorkflowActionsDisplayed() {

        try {

            WebElement card =
                    getFirstWorkflowCard();

            /*
             * The action controls are associated with
             * the workflow card and may become available
             * when the card is hovered.
             */
            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            card
                    );

            new Actions(driver)
                    .moveToElement(card)
                    .perform();

            /*
             * Wait until all five action controls
             * are present inside the first card.
             */
            wait.until(driver -> {

                try {

                    List<WebElement> cards =
                            driver.findElements(
                                    workflowCards
                            );

                    if (cards.isEmpty()) {

                        return false;
                    }

                    WebElement currentCard =
                            cards.get(0);

                    List<WebElement> builder =
                            currentCard.findElements(
                                    builderButton
                            );

                    List<WebElement> settings =
                            currentCard.findElements(
                                    settingsButton
                            );

                    List<WebElement> edit =
                            currentCard.findElements(
                                    editButton
                            );

                    List<WebElement> duplicate =
                            currentCard.findElements(
                                    duplicateButton
                            );

                    List<WebElement> delete =
                            currentCard.findElements(
                                    deleteButton
                            );

                    return !builder.isEmpty()
                            && !settings.isEmpty()
                            && !edit.isEmpty()
                            && !duplicate.isEmpty()
                            && !delete.isEmpty();

                } catch (Exception e) {

                    return false;
                }
            });

            /*
             * Get a fresh card after the DOM has
             * finished rendering.
             */
            card =
                    getFirstWorkflowCard();

            WebElement builder =
                    card.findElement(
                            builderButton
                    );

            WebElement settings =
                    card.findElement(
                            settingsButton
                    );

            WebElement edit =
                    card.findElement(
                            editButton
                    );

            WebElement duplicate =
                    card.findElement(
                            duplicateButton
                    );

            WebElement delete =
                    card.findElement(
                            deleteButton
                    );

            System.out.println(
                    "========== WORKFLOW ACTION DEBUG =========="
            );

            System.out.println(
                    "Builder   : " +
                            builder.isDisplayed()
            );

            System.out.println(
                    "Settings  : " +
                            settings.isDisplayed()
            );

            System.out.println(
                    "Edit      : " +
                            edit.isDisplayed()
            );

            System.out.println(
                    "Duplicate : " +
                            duplicate.isDisplayed()
            );

            System.out.println(
                    "Delete    : " +
                            delete.isDisplayed()
            );

            System.out.println(
                    "============================================"
            );

            return builder.isDisplayed()
                    && settings.isDisplayed()
                    && edit.isDisplayed()
                    && duplicate.isDisplayed()
                    && delete.isDisplayed();

        } catch (Exception e) {

            System.out.println(
                    "Workflow actions validation failed: "
                            + e.getMessage()
            );

            return false;
        }
    }

    // =========================
    // Search
    // =========================

    public void searchWorkflow(String workflowNameValue) {

        WebElement search =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(searchBox)
                );

        search.clear();
        search.sendKeys(workflowNameValue);

        wait.until(driver -> {

            List<WebElement> cards =
                    driver.findElements(workflowCards);

            if (cards.isEmpty()) {
                return false;
            }

            for (WebElement card : cards) {

                List<WebElement> names =
                        card.findElements(workflowName);

                for (WebElement name : names) {

                    String actualWorkflowName =
                            name.getText().trim();

                    if (actualWorkflowName.equalsIgnoreCase(
                            workflowNameValue.trim())) {

                        return true;
                    }
                }
            }

            return false;
        });
    }
    public boolean isSearchFilterDisplayed(
            String workflowNameValue
    ) {

        try {

            List<WebElement> filters =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfAllElementsLocatedBy(
                                            searchFilter
                                    )
                    );

            String expectedText =
                    "Search: " +
                            workflowNameValue;

            for (WebElement filter : filters) {

                if (filter.getText()
                        .trim()
                        .equals(expectedText)) {

                    return true;
                }
            }

            return false;

        } catch (Exception e) {

            return false;
        }
    }

    public void removeIndividualFilter() {

        WebElement removeButton =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        removeSearchFilterButton
                                )
                );

        removeButton.click();

        wait.until(
                driver ->
                        driver.findElements(
                                removeSearchFilterButton
                        ).isEmpty()
        );
    }

    // =========================
    // Clear filters
    // =========================

    public void clickClearFilters() {

        WebElement clearButton =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        clearFiltersButton
                                )
                );

        clearButton.click();

        wait.until(
                ExpectedConditions
                        .invisibilityOfElementLocated(
                                activeFiltersSection
                        )
        );
    }

    public boolean isActiveFiltersSectionDisplayed() {

        try {

            List<WebElement> elements =
                    driver.findElements(
                            activeFiltersSection
                    );

            return !elements.isEmpty()
                    && elements.get(0).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isSearchBoxEmpty() {

        try {

            WebElement search =
                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            searchBox
                                    )
                    );

            String value =
                    search.getAttribute(
                            "value"
                    );

            return value == null
                    || value.trim().isEmpty();

        } catch (Exception e) {

            return false;
        }
    }

    // =========================
    // Workflow details
    // =========================

    public void openWorkflow() {

        WebElement firstWorkflow =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        workflowLink
                                )
                );

        firstWorkflow.click();

        wait.until(
                driver ->
                        driver.getCurrentUrl()
                                .contains("/workflows/")
        );
    }

    public boolean isDetailsPageDisplayed() {

        try {

            return wait.until(
                    driver ->
                            driver.getCurrentUrl()
                                    .matches(
                                            ".*/workflows/[^/]+.*"
                                    )
            );

        } catch (Exception e) {

            return false;
        }
    }
}