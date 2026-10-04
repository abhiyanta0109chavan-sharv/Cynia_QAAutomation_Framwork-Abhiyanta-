Feature: Workflow Screen

  Background:
    Given user is logged into the Cynia application
    And user navigates to the Workflows screen

  Scenario: Verify Workflow screen loads successfully
    Then the Workflows screen should be displayed

  Scenario: Verify workflows are displayed
    Then the workflow list should be displayed

  Scenario: Validate workflow information
    Then workflow name and workflow status should be displayed

  Scenario: Verify workflow actions
    Then available actions for the workflow should be displayed

  Scenario: Verify workflow search
    When user searches for a workflow
    Then the matching workflow should be displayed
    And the search filter should be displayed
    When user removes the search filter
    Then the search filter should be removed

  Scenario: Verify clear filters
    When user searches for a workflow
    And user clicks Clear filters
    Then all active filters should be cleared
    And the workflow search box should be empty

  Scenario: Verify navigation to workflow details
    When user opens a workflow
    Then the workflow details screen should be displayed