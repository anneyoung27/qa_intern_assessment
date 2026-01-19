Feature: Sales Master Data Management
  As a QA Tester
  I want to create master data for customers and products
  So that I can use them in sales transactions

  Background:
    Given user is on Odoo login page
    When user logs in with valid credentials
    Then user should be redirected to dashboard

  Scenario: Create new customer in Sales module
    Given user navigates to Sales module
    When user clicks on Orders menu
    And user clicks on Customers sub menu
    And user clicks on Create button for customer
    And user clicks on Person radio button
    And user fills customer form with following details
      | Name         | Injas Mahendra Berutu |
      | Email        | mahendra@test.com     |
      | Phone        | 081234567890          |
      | Street       | Jl. Test No. 123      |
      | City         | Jakarta               |
      | ZIP          | 12345                 |
      | Job Position | QA Engineer           |
    And user clicks Save button for customer
    Then customer should be created successfully
    And customer name "Injas Mahendra Berutu" should be displayed

  Scenario: Create new product in Sales module
    Given user navigates to Sales module
    When user clicks on Products menu
    And user clicks on Products sub menu
    And user clicks on Create button for product
    And user fills product form with following details
      | Product Name       | Razer Gaming laptop |
      | Product Type       | Goods               |
      | Sales Price        | 100000              |
      | Cost               | 75000               |
      | Internal Reference | PROD-002            |
    And user clicks Save button for product
    Then product should be created successfully
    And product name "Razer Gaming laptop" should be displayed
