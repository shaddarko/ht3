import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomeTask3 {


    private WebDriver driver;
    private Logger logger = LogManager.getLogger(HomeTask3.class);

    public WebElement waitUntilClickable(String xPathLocator) {

        return new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(new By.ByXPath(xPathLocator)));
    }


    @BeforeTest
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        logger.info("Initializing webdriver...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Driver initialized...");


    }

    @Test
    public void yandex() throws InterruptedException {


        logger.info("Opening target page");
        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list?hid=91491&onstock=1");
        logger.info("Target page opened");
        waitUntilClickable("//span[contains(text(),'HONOR')]").click();
        waitUntilClickable("//span[contains(text(),'Xiaomi')]").click();
        waitUntilClickable("//button[@data-autotest-id='dprice']").click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[contains(text(),'Redmi')]/ancestor::article//div[contains(@aria-label,'сравнению')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'HONOR')]/ancestor::article//div[contains(@aria-label,'сравнению')]")).click();
        waitUntilClickable("//span[contains(text(),'Сравнить')]").click();
        waitUntilClickable("//button[contains(text(),'Все характеристики')]").click();
        waitUntilClickable("//div[contains(text(),'Операционная система')]");
        waitUntilClickable("//button[contains(text(),'Различающиеся характеристики')]").click();
        Boolean isPresent = driver.findElements(By.xpath("//div[contains(text(),'Операционная система')]")).size() > 0;


    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing driver...");
            driver.close();
            logger.info("Driver closed...");
        }

    }
}