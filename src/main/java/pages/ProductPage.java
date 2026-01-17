package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    // Form field locators - updated
    private By productNameField = By.xpath("//textarea[@id='name_0']");
    private By productTypeGoods = By.xpath("//label[normalize-space()='Goods']");
    private By salesPriceField = By.xpath("//input[@id='list_price_0']");
    private By costField = By.xpath("//input[@id='standard_price_0']");
    private By internalRefField = By.xpath("//input[@id='default_code_0']");
    private By saveButton = By.xpath("//button[normalize-space()='Send message']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void fillProductName(String productName) {
        if (productName != null && !productName.isEmpty()) {
            System.out.println("Filling product name: " + productName);
            waitAndSendKeys(productNameField, productName);
        }
    }

    public void selectProductType(String productType) {
        if (productType != null && productType.equalsIgnoreCase("Goods")) {
            System.out.println("Selecting product type: " + productType);
            waitAndClick(productTypeGoods);
        }
    }

    public void fillSalesPrice(String salesPrice) {
        if (salesPrice != null && !salesPrice.isEmpty()) {
            System.out.println("Filling sales price: " + salesPrice);
            waitAndSendKeys(salesPriceField, salesPrice);
        }
    }

    public void fillCost(String cost) {
        if (cost != null && !cost.isEmpty()) {
            System.out.println("Filling cost: " + cost);
            waitAndSendKeys(costField, cost);
        }
    }

    public void fillInternalReference(String internalRef) {
        if (internalRef != null && !internalRef.isEmpty()) {
            System.out.println("Filling internal reference: " + internalRef);
            waitAndSendKeys(internalRefField, internalRef);
        }
    }

    public void fillProductForm(String productName, String productType, String salesPrice,
                                String cost, String internalRef) {
        fillProductName(productName);
        selectProductType(productType);
        fillSalesPrice(salesPrice);
        fillCost(cost);
        fillInternalReference(internalRef);
    }

    public void clickSave() {
        System.out.println("Clicking Save button for product");
        waitAndClick(saveButton);
        sleep(3000); // Wait for save to complete
    }

    public boolean isProductCreated(String productName) {
        sleep(2000);
        try {
            String titleText = driver.findElement(productNameField).getAttribute("value");
            System.out.println("Product name displayed: " + titleText);
            return titleText.contains(productName);
        } catch (Exception e) {
            System.err.println("Error verifying product creation: " + e.getMessage());
            return false;
        }
    }
}
