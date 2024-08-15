package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Pages.Utils.highlightElement;


public class HomePage {
    private WebDriver driver;
    private By whitePapersDropdownItem =  By.xpath("//span[text()='White Papers & eBooks']");
    private By insightTab = By.linkText("INSIGHTS");
    Duration duration = Duration.ofMillis(1000);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToInsights() {
        //Action to hover over the "INSIGHTS" menu item
        WebElement insightsMenu = driver.findElement(insightTab);
        Actions actions = new Actions(driver);
        actions.moveToElement(insightsMenu).perform();
    }

    public void clickOnWhitePapersAndEbook() {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        // Wait until the dropdown is visible and clickable
        WebElement whitePapersEbooksOption = wait.until(ExpectedConditions.elementToBeClickable(whitePapersDropdownItem));
        highlightElement(driver, whitePapersEbooksOption);
        whitePapersEbooksOption.click();
    }


}
