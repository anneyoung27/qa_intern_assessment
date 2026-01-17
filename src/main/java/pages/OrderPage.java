package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.StaleElementReferenceException;

public class OrderPage extends BasePage {

    // Menu locators
    private By salesModuleButton = By.xpath("//a[@id='result_app_7']//img[@class='o_app_icon rounded-3']");
    private By ordersMenu = By.xpath("//span[normalize-space()='Orders']");
    private By customersSubmenu = By.xpath("//a[normalize-space()='Customers']");

    // Button locators - fixed xpath
    private By createButton = By.xpath("//button[normalize-space()='New']");
    private By saveButton = By.xpath("//button[contains(@class,'o_form_button_save') or contains(text(),'Save')]");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void clickSalesModule() {
        System.out.println("Clicking Sales module");
        waitAndClick(salesModuleButton);
        sleep(2000); // Wait for page to load
    }

    public void clickOrdersMenu() {
        System.out.println("Clicking Orders menu");
        waitAndClick(ordersMenu);
        sleep(1500); // Wait for submenu to appear
    }

    public void clickCustomersSubmenu() {
        System.out.println("Clicking Customers submenu");
        waitAndClick(customersSubmenu);
        sleep(3000); // Wait for customers page to load completely
    }

    public void clickCreate() {
        System.out.println("Clicking Create button");
        // Retry mechanism to handle stale element
        int attempts = 0;
        while (attempts < 3) {
            try {
                sleep(2000); // Wait before finding element
                waitAndClick(createButton);
                System.out.println("Create button clicked successfully");
                sleep(2000); // Wait for form to load
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("Stale element, retrying... Attempt: " + attempts);
                sleep(1500);
                if (attempts >= 3) {
                    throw e;
                }
            }
        }
    }

    public void clickSave() {
        System.out.println("Clicking Save button");
        waitAndClick(saveButton);
        sleep(3000);
    }
}
