Feature: Flow 4 - Workflow Execution

  Background:
    Given user is logged into the Cynia application
    And user navigates to the Workflows screen


  Scenario: Verify Workflows screen before execution
    Then the Workflows heading should be displayed


  Scenario: Verify Store Printer Invoice Remediation workflow screen
    When user opens the ITSM Store Printer Invoice Remediation workflow
    Then the ITSM Store Printer Invoice Remediation heading should be displayed


  Scenario: Verify Builder screen before execution
    When user opens the ITSM Store Printer Invoice Remediation workflow
    Then the ITSM Store Printer Invoice Remediation heading should be displayed
    When user opens the workflow builder
    Then the Test draft workflow button should be displayed


  Scenario: Verify Test draft workflow dialog before execution
    When user opens the ITSM Store Printer Invoice Remediation workflow
    Then the ITSM Store Printer Invoice Remediation heading should be displayed
    When user opens the workflow builder
    Then the Test draft workflow button should be displayed
    When user clicks the Test draft workflow button
    Then the Test draft workflow dialog should be displayed


  Scenario: Assign queue to workflow and verify queue assignment
    When user opens the ITSM Store Printer Invoice Remediation workflow
    Then the ITSM Store Printer Invoice Remediation heading should be displayed
    When user opens the workflow builder
    Then the Test draft workflow button should be displayed
    When user assigns the required queue if needed
    Then the queue assignment should be completed


  Scenario: Execute workflow and verify execution trace
    When user opens the ITSM Store Printer Invoice Remediation workflow
    Then the ITSM Store Printer Invoice Remediation heading should be displayed
    When user opens the workflow builder
    Then the Test draft workflow button should be displayed
    When user clicks the Test draft workflow button
    Then the Test draft workflow dialog should be displayed
    When user enters valid inputData JSON
    Then the Test draft button should be enabled
    When user clicks the Test draft button
    Then the execution trace should be displayed


  Scenario: Execute workflow, verify Traces, statuses and return to workflow overview
    When user opens the ITSM Store Printer Invoice Remediation workflow
    Then the ITSM Store Printer Invoice Remediation heading should be displayed
    When user opens the workflow builder
    Then the Test draft workflow button should be displayed
    When user clicks the Test draft workflow button
    Then the Test draft workflow dialog should be displayed
    When user enters valid inputData JSON
    Then the Test draft button should be enabled
    When user clicks the Test draft button
    Then the execution trace should be displayed
    And the Traces heading should be displayed
    And the current workflow execution status should be displayed
    And the trace statuses should be displayed
    And the human response limitation should be reported
    When user navigates back to the workflow overview
    Then the workflow overview should be displayed