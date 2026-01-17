package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

public class ProductMenuPage extends BasePage {

    // Menu locators
    private By productsMenu = By.xpath("//span[normalize-space()='Products']");
    private By productsSubmenu = By.xpath("//a[normalize-space()='Products']");
    private By createButton = By.xpath("//button[normalize-space()='New']");

    public ProductMenuPage(WebDriver driver) {
        super(driver);
    }

    public void clickProductsMenu() {
        System.out.println("Clicking Products menu");
        waitAndClick(productsMenu);
        sleep(1500); // Wait for submenu to appear
    }

    public void clickProductsSubmenu() {
        System.out.println("Clicking Products submenu");
        waitAndClick(productsSubmenu);
        sleep(3000); // Wait for products page to load completely
    }

    public void clickCreate() {
        System.out.println("Clicking Create button for product");
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
}
