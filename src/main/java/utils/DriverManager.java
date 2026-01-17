package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser configuration
     */
    public static void initializeDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();

        switch (browser) {
            case "chrome":
                setupChromeDriver();
                break;
            case "firefox":
                setupFirefoxDriver();
                break;
            case "edge":
                setupEdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser '" + browser + "' is not supported. Please use chrome, firefox, or edge");
        }

        configureDriver();
        System.out.println("WebDriver initialized successfully: " + browser);
    }

    /**
     * Setup Chrome driver
     */
    private static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        // Uncomment for headless mode
        // options.addArguments("--headless");
        driver.set(new ChromeDriver(options));
    }

    /**
     * Setup Firefox driver
     */
    private static void setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        // Uncomment for headless mode
        // options.addArguments("--headless");
        driver.set(new FirefoxDriver(options));
    }

    /**
     * Setup Edge driver
     */
    private static void setupEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        // Uncomment for headless mode
        // options.addArguments("--headless");
        driver.set(new EdgeDriver(options));
    }

    private static void configureDriver() {
        WebDriver webDriver = driver.get();
        webDriver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(ConfigReader.getImplicitWait())
        );
        webDriver.manage().timeouts().pageLoadTimeout(
            Duration.ofSeconds(30)
        );
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new RuntimeException("WebDriver is not initialized. Call initializeDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
                System.out.println("WebDriver quit successfully");
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }

    public static void navigateTo(String url) {
        getDriver().get(url);
        System.out.println("Navigated to: " + url);
    }

    public static void navigateToBaseUrl() {
        navigateTo(ConfigReader.getBaseUrl());
    }


    public static String getPageTitle() {
        return getDriver().getTitle();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void refreshPage() {
        getDriver().navigate().refresh();
        System.out.println("Page refreshed");
    }
}

