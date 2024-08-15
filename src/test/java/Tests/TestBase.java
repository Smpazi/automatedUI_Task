package Tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.util.logging.Logger;

public class TestBase {
        protected WebDriver driver;
        protected static final Logger logger = Logger.getLogger(String.valueOf(TestBase.class));

    @BeforeClass
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://www.kurtosys.com/");
            logger.info("WebDriver initialized and browser maximized.");
        }

        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed.");
            }
        }


}
