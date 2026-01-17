package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerPage extends BasePage {

    // Form field locators
    private By personRadioButton = By.xpath("//label[normalize-space()='Person']");
    private By nameField = By.xpath("//input[@id='name_1']");
    private By emailField = By.xpath("//input[@id='email_0']");
    private By phoneField = By.xpath("//input[@id='phone_0']");
    private By streetField = By.xpath("//input[@id='street_0']");
    private By cityField = By.xpath("//input[@id='city_0']");
    private By zipField = By.xpath("//input[@id='zip_0']");
    private By jobPositionField = By.xpath("//input[@id='function_0']");
    private By saveButton = By.xpath("//button[normalize-space()='Send message']");

    public CustomerPage(WebDriver driver) {
        super(driver);
    }

    public void clickPersonRadioButton() {
        System.out.println("Clicking Person radio button");
        waitAndClick(personRadioButton);
    }

    public void fillCustomerName(String name) {
        if (name != null && !name.isEmpty()) {
            System.out.println("Filling customer name: " + name);
            waitAndSendKeys(nameField, name);
        }
    }

    public void fillCustomerEmail(String email) {
        if (email != null && !email.isEmpty()) {
            System.out.println("Filling customer email: " + email);
            waitAndSendKeys(emailField, email);
        }
    }

    public void fillCustomerPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            System.out.println("Filling customer phone: " + phone);
            waitAndSendKeys(phoneField, phone);
        }
    }

    public void fillCustomerStreet(String street) {
        if (street != null && !street.isEmpty()) {
            System.out.println("Filling customer street: " + street);
            waitAndSendKeys(streetField, street);
        }
    }

    public void fillCustomerCity(String city) {
        if (city != null && !city.isEmpty()) {
            System.out.println("Filling customer city: " + city);
            waitAndSendKeys(cityField, city);
        }
    }

    public void fillCustomerZip(String zip) {
        if (zip != null && !zip.isEmpty()) {
            System.out.println("Filling customer zip: " + zip);
            waitAndSendKeys(zipField, zip);
        }
    }

    public void fillJobPosition(String jobPosition) {
        if (jobPosition != null && !jobPosition.isEmpty()) {
            System.out.println("Filling job position: " + jobPosition);
            waitAndSendKeys(jobPositionField, jobPosition);
        }
    }

    public void fillCustomerForm(String name, String email, String phone, String street,
                                 String city, String zip, String jobPosition) {
        fillCustomerName(name);
        fillCustomerEmail(email);
        fillCustomerPhone(phone);
        fillCustomerStreet(street);
        fillCustomerCity(city);
        fillCustomerZip(zip);
        fillJobPosition(jobPosition);
    }

    public void clickSave() {
        System.out.println("Clicking Save button");
        waitAndClick(saveButton);
    }

    public boolean isCustomerCreated(String customerName) {
        sleep(2000);
        try {
            String titleText = driver.findElement(nameField).getAttribute("value");
            System.out.println("Customer name displayed: " + titleText);
            return titleText.contains(customerName);
        } catch (Exception e) {
            System.err.println("Error verifying customer creation: " + e.getMessage());
            return false;
        }
    }
}
