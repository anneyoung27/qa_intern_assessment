package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By usernameField = By.xpath("//input[@id='login' or @name='login']");
    private By passwordField = By.xpath("//input[@id='password' or @name='password']");
    private By loginButton = By.xpath("//button[@type='submit' or contains(text(),'Log in')]");
    private By dashboardHeader = By.xpath("//nav[contains(@class,'o_main_navbar')] | //div[contains(@class,'o_action_manager')] | //a[@data-menu-xmlid]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        System.out.println("Attempting to login with username: " + username);
        waitAndSendKeys(usernameField, username);
        waitAndSendKeys(passwordField, password);
        waitAndClick(loginButton);
        sleep(3000);
    }

    public boolean isDashboardDisplayed() {
        sleep(2000);
        return isElementDisplayed(dashboardHeader);
    }
}
