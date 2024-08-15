package Tests;

import Pages.HomePage;

import Pages.WhitePapersPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import static org.testng.Reporter.*;

public class FunctionalTest extends TestBase {
    @Test
    public void testWhitePapersForm() {
        Duration duration = Duration.ofSeconds(10);

        // Wait for the cookie consent to appear and click to close it
        WebDriverWait wait = new WebDriverWait(driver, duration);
        WebElement cookieBanner = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
        cookieBanner.click();

        HomePage homePage = new HomePage(driver);
        WhitePapersPage whitePapersPage = new WhitePapersPage(driver);

        // Step 2: Navigate to “INSIGHTS”
        homePage.navigateToInsights();
        logger.info("Hovered over 'INSIGHTS'");

        // Step 3: From the dropdown, click on “White Papers & eBooks”
        homePage.clickOnWhitePapersAndEbook();
        logger.info("Clicked on 'White Papers & eBooks'");

        // Step 4: Verify Title reads “White Papers”
        Boolean pageTitle = whitePapersPage.verifyTitle();
        Assert.assertEquals(pageTitle , true);
        logger.info("....");

        // Step 5: Click on “UCITS Whitepaper”
        whitePapersPage.clickOnUCITSWhitePaper();
        logger.info("Clicked on 'UCITS Whitepaper'");

        // 6-9. Fill in form fields (First Name, Last Name, Company, Industry)
        whitePapersPage.fillForm("Salomi", "Mpazi", "Tech", "Insure");
        logger.info("Filled in First Name, Last Name, Company, and Industry fields");

        // 10. Do not fill in the "Email" field
        whitePapersPage.noEmail();

        // 11. Click "Send me a copy"
        whitePapersPage.clickOnSendButton();
        logger.info("Clicked 'Send me a copy' button");

        // 13. Validate all error messages
        Assert.assertTrue(whitePapersPage.noEmail(), "Email error message is not displayed");
        logger.info("Email error message validated");
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
                String screenshotPath = "screenshots/" + result.getMethod().getMethodName() + ".png";
                Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
                Reporter.log("Screenshot taken: " + screenshotPath);
                logger.info("Screenshot captured: " + screenshotPath);
            } catch (WebDriverException | IOException e) {
                logger.warning("Failed to capture screenshot: " + e.getMessage());
            }
        }


    }
}