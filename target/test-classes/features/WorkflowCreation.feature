Feature: Create Workflow from Template

  Background:
    Given user is logged into the Cynia application
    And user navigates to the Workflows screen


  @Flow3
  @CreateFromStarter
  Scenario: Navigate to workflow templates
    When the user clicks Create from starter
    Then the workflow template selection page should be displayed


  @Flow3
  @SelectTemplate
  Scenario: Select Store Printer Invoice Remediation template
    When the user clicks Create from starter
    And the user selects the Store Printer Invoice Remediation template
    Then the Store Printer Invoice Remediation template should be selected


  @Flow3
  @CreateWorkflow
  Scenario: Create workflow from template
    When the user clicks Create from starter
    And the user selects the Store Printer Invoice Remediation template
    And the user clicks Next
    And the user updates the workflow key
    And the user clicks Next
    And the user skips identity and creates the workflow
    Then the configure workflow dialog should be displayed


  @Flow3
  @ConfigureWorkflow
  Scenario: Configure workflow risk threshold
    When the user clicks Create from starter
    And the user selects the Store Printer Invoice Remediation template
    And the user clicks Next
    And the user updates the workflow key
    And the user clicks Next
    And the user skips identity and creates the workflow
    And the user sets risk threshold to "0.85"
    Then the risk threshold should be "0.85"


  @Flow3
  @SaveWorkflow
  Scenario: Save workflow configuration
    When the user clicks Create from starter
    And the user selects the Store Printer Invoice Remediation template
    And the user clicks Next
    And the user updates the workflow key
    And the user clicks Next
    And the user skips identity and creates the workflow
    And the user sets risk threshold to "0.85"
    And the user clicks Save knobs
    Then the workflow should be created successfully


  @Flow3
  @VerifyCreation
  Scenario: Verify successful workflow creation
    When the user clicks Create from starter
    And the user selects the Store Printer Invoice Remediation template
    And the user clicks Next
    And the user updates the workflow key
    And the user clicks Next
    And the user skips identity and creates the workflow
    And the user sets risk threshold to "0.85"
    And the user clicks Save knobs
    Then the workflow should be created successfully


  @Flow3
  @VerifyWorkflowList
  Scenario: Verify newly created workflow appears in workflow list
    When the user clicks Create from starter
    And the user selects the Store Printer Invoice Remediation template
    And the user clicks Next
    And the user updates the workflow key
    And the user clicks Next
    And the user skips identity and creates the workflow
    And the user sets risk threshold to "0.85"
    And the user clicks Save knobs
    And the user navigates back to Workflows
    And the user searches for the newly created workflow
    Then the newly created workflow should appear in the workflow list