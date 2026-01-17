package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SalesPage extends BasePage {

    private By salesMenu = By.xpath("//a[contains(@data-menu-xmlid,'sale') or contains(text(),'Sales')]");
    private By customersMenu = By.xpath("//a[contains(text(),'Customers') or contains(@href,'partner')]");
    private By productsMenu = By.xpath("//a[contains(text(),'Products') or contains(@href,'product')]");
    private By createButton = By.xpath("//button[contains(@class,'o_list_button_add') or contains(text(),'New') or contains(text(),'Create')]");
    private By saveButton = By.xpath("//button[contains(@class,'o_form_button_save') or contains(text(),'Save')]");

    public SalesPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToSales() {
        try {
            waitAndClick(salesMenu);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickCustomersMenu() {
        try {
            waitAndClick(customersMenu);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickProductsMenu() {
        try {
            waitAndClick(productsMenu);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickCreate() {
        try {
            waitAndClick(createButton);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickSave() {
        try {
            waitAndClick(saveButton);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
