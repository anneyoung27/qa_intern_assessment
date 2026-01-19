package hooks;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("========== Test Execution Started ==========");
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        // Pastikan tidak ada driver yang sudah aktif
        if (DriverManager.getDriver() == null) {
            DriverManager.initializeDriver();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                System.out.println("Scenario failed: " + scenario.getName());

                // Capture screenshot on failure
                if (DriverManager.getDriver() != null) {
                    byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Failed Screenshot");
                }
            } else if (scenario.getStatus().toString().equalsIgnoreCase("PASSED")) {
                System.out.println("Scenario passed: " + scenario.getName());
            } else if (scenario.getStatus().toString().equalsIgnoreCase("SKIPPED")) {
                System.out.println("Scenario skipped: " + scenario.getName());
            }

            System.out.println("Closing browser for scenario: " + scenario.getName());
        } finally {
            // Pastikan driver ditutup
            if (DriverManager.getDriver() != null) {
                DriverManager.quitDriver();
            }
        }
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("========== Test Execution Completed ==========");
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("Completed step in scenario: " + scenario.getName());
    }
}
