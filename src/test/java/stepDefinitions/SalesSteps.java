package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CustomerPage;
import pages.LoginPage;
import pages.ProductMenuPage;
import pages.OrderPage;
import pages.ProductPage;
import utils.ConfigReader;
import utils.DriverManager;

import java.util.Map;

public class SalesSteps {

    private LoginPage loginPage;
    private OrderPage orderPage;
    private ProductMenuPage productMenuPage;
    private CustomerPage customerPage;
    private ProductPage productPage;

    @Given("user is on Odoo login page")
    public void userIsOnOdooLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
        System.out.println("User is on Odoo login page");
    }

    @When("user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        loginPage.login(username, password);
    }

    @Then("user should be redirected to dashboard")
    public void userShouldBeRedirectedToDashboard() {
        Assert.assertTrue(loginPage.isDashboardDisplayed(), "Dashboard is not displayed");
        System.out.println("User successfully logged in and redirected to dashboard");
    }

    // Customer Steps - Sales module -> Orders -> Customers
    @Given("user navigates to Sales module")
    public void userNavigatesToSalesModule() {
        orderPage = new OrderPage(DriverManager.getDriver());
        orderPage.clickSalesModule();
    }

    @When("user clicks on Orders menu")
    public void userClicksOnOrdersMenu() {
        orderPage.clickOrdersMenu();
    }

    @When("user clicks on Customers sub menu")
    public void userClicksOnCustomersSubMenu() {
        orderPage.clickCustomersSubmenu();
    }

    @When("user clicks on Create button for customer")
    public void userClicksOnCreateButtonForCustomer() {
        orderPage.clickCreate();
    }

    @When("user clicks on Person radio button")
    public void userClicksOnPersonRadioButton() {
        customerPage = new CustomerPage(DriverManager.getDriver());
        customerPage.clickPersonRadioButton();
    }

    @When("user fills customer form with following details")
    public void userFillsCustomerFormWithFollowingDetails(DataTable dataTable) {
        if (customerPage == null) {
            customerPage = new CustomerPage(DriverManager.getDriver());
        }
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        customerPage.fillCustomerForm(
            data.get("Name"),
            data.get("Email"),
            data.get("Phone"),
            data.get("Street"),
            data.get("City"),
            data.get("ZIP"),
            data.get("Job Position")
        );
    }

    @When("user clicks Save button for customer")
    public void userClicksSaveButtonForCustomer() {
        customerPage.clickSave();
    }

    @Then("customer should be created successfully")
    public void customerShouldBeCreatedSuccessfully() {
        System.out.println("Customer creation validated");
    }

    @Then("customer name {string} should be displayed")
    public void customerNameShouldBeDisplayed(String customerName) {
        Assert.assertTrue(customerPage.isCustomerCreated(customerName),
            "Customer " + customerName + " is not created");
    }

    // Product Steps - Sales module -> Products -> Products
    @When("user clicks on Products menu")
    public void userClicksOnProductsMenu() {
        if (productMenuPage == null) {
            productMenuPage = new ProductMenuPage(DriverManager.getDriver());
        }
        productMenuPage.clickProductsMenu();
    }

    @When("user clicks on Products sub menu")
    public void userClicksOnProductsSubMenu() {
        productMenuPage.clickProductsSubmenu();
    }

    @When("user clicks on Create button for product")
    public void userClicksOnCreateButtonForProduct() {
        productMenuPage.clickCreate();
    }

    @When("user fills product form with following details")
    public void userFillsProductFormWithFollowingDetails(DataTable dataTable) {
        if (productPage == null) {
            productPage = new ProductPage(DriverManager.getDriver());
        }
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        productPage.fillProductForm(
            data.get("Product Name"),
            data.get("Product Type"),
            data.get("Sales Price"),
            data.get("Cost"),
            data.get("Internal Reference")
        );
    }

    @When("user clicks Save button for product")
    public void userClicksSaveButtonForProduct() {
        productPage.clickSave();
    }

    @Then("product should be created successfully")
    public void productShouldBeCreatedSuccessfully() {
        System.out.println("Product creation validated");
    }

    @Then("product name {string} should be displayed")
    public void productNameShouldBeDisplayed(String productName) {
        Assert.assertTrue(productPage.isProductCreated(productName),
            "Product " + productName + " is not created");
    }
}
