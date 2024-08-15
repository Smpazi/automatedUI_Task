package Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Utils {
    public Utils() {
    }

    // Utility method to highlight an element
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
        try {
            Thread.sleep(2000); // Wait for 2 seconds to make the highlight visible
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
