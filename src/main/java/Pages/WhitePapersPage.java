package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Pages.Utils.highlightElement;


public class WhitePapersPage {
    private WebDriver driver;
    private By whitePapersTitle = By.xpath("//h2[contains(.,'White Papers')]");
    private By ucitsWhitePaperLink = By.linkText("UCITS White Paper");
    private By nameField = By.name("first-name");
    private By surnameField = By.name("last-name");
    private By companyField = By.name("company");
    private By industryField = By.name("industry");
    Duration duration = Duration.ofMillis(1000);
    WebDriverWait wait;


    public WhitePapersPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean verifyTitle() {
        return driver.findElement(whitePapersTitle).isDisplayed();
    }

    public void clickOnUCITSWhitePaper() {
         wait = new WebDriverWait(driver, duration);
        // Wait until the dropdown is visible and clickable
        WebElement whitePapersEbooksOption = wait.until(ExpectedConditions.elementToBeClickable(ucitsWhitePaperLink));
        highlightElement(driver, whitePapersEbooksOption);
        whitePapersEbooksOption.click();
    }

    public void fillForm(String name,String surname, String company, String industry ){
        wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(surnameField)).sendKeys(surname);
        wait.until(ExpectedConditions.visibilityOfElementLocated(companyField)).sendKeys(company);
        wait.until(ExpectedConditions.visibilityOfElementLocated(industryField)).sendKeys(industry);
    }
    public void clickOnSendButton(){
        driver.findElement(By.xpath("//button[text()='Send me a copy']")).click();
    }

    public boolean noEmail(){

        driver.findElement(By.xpath("//label[contains(text(),'Email')]//following-sibling::span[@class='hs-error-msg']")).isDisplayed();
        return false;
    }
}
