package hooks;

import com.cynia.automation.config.configReader;
import com.cynia.automation.driver.driverfactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    @Before
    public void setUp() {

        System.out.println("========== HOOK START ==========");

        String browser =
                configReader.getProperty("browser");

        String url =
                configReader.getProperty("url");

        System.out.println(
                "Browser from config: " + browser
        );

        System.out.println(
                "URL from config: " + url
        );

        driverfactory.initializeDriver(browser);

        System.out.println("Browser initialized.");

        driverfactory.getDriver().get(url);

        System.out.println(
                "URL opened: " +
                        driverfactory.getDriver().getCurrentUrl()
        );

        System.out.println("========== HOOK END ==========");
    }


    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver =
                driverfactory.getDriver();

        if (driver != null) {

            // ========================================
            // Capture screenshot ONLY if scenario fails
            // ========================================

            if (scenario.isFailed()) {

                try {

                    // Keep browser visible before screenshot
                    Thread.sleep(3000);

                    // Capture screenshot
                    byte[] screenshot =
                            ((TakesScreenshot) driver)
                                    .getScreenshotAs(
                                            OutputType.BYTES
                                    );

                    // Attach screenshot to Cucumber report
                    scenario.attach(
                            screenshot,
                            "image/png",
                            scenario.getName()
                    );

                    // Create safe scenario name
                    String scenarioName =
                            scenario.getName()
                                    .replaceAll(
                                            "[^a-zA-Z0-9-_]",
                                            "_"
                                    );

                    // Timestamp for unique filename
                    String timestamp =
                            LocalDateTime.now().format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyyMMdd_HHmmss_SSS"
                                    )
                            );

                    String fileName =
                            scenarioName
                                    + "_FAILED_"
                                    + timestamp
                                    + ".png";

                    // Screenshot folder
                    Path screenshotDirectory =
                            Paths.get(
                                    "target",
                                    "screenshots"
                            );

                    Files.createDirectories(
                            screenshotDirectory
                    );

                    // Final screenshot path
                    Path screenshotPath =
                            screenshotDirectory.resolve(
                                    fileName
                            );

                    // Save screenshot
                    Files.write(
                            screenshotPath,
                            screenshot
                    );

                    System.out.println(
                            "Failed scenario screenshot saved: " +
                                    screenshotPath.toAbsolutePath()
                    );

                } catch (IOException e) {

                    System.out.println(
                            "Unable to save screenshot: " +
                                    e.getMessage()
                    );

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    System.out.println(
                            "Screenshot wait interrupted."
                    );
                }
            }

            // ========================================
            // Close browser
            // ========================================

            driverfactory.quitDriver();

            System.out.println("Browser closed.");
        }
    }
}