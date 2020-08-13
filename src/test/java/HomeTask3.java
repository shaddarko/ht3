import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomeTask3 {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(HomeTask3.class);


    @BeforeTest
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Initializing webdriver...");


    }

    @Test
    public void yandex() throws InterruptedException {
        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list?hid=91491&onstock=1");
        logger.info("Opening target page");
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//span[contains(text(),'HONOR')]"))).click();
        driver.findElement(By.xpath("//span[contains(text(),'Xiaomi')]")).click();
        driver.findElement(By.xpath("//button[@data-autotest-id='dprice']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[contains(text(),'Redmi')]/ancestor::article//div[contains(@aria-label,'сравнению')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'HONOR')]/ancestor::article//div[contains(@aria-label,'сравнению')]")).click();
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//span[contains(text(),'Сравнить')]"))).click();
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//button[contains(text(),'Все характеристики')]"))).click();
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//div[contains(text(),'Операционная система')]")));
        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("//button[contains(text(),'Различающиеся характеристики')]"))).click();
        Boolean isPresent = driver.findElements(By.xpath("//div[contains(text(),'Операционная система')]")).size() > 0;

    }

    @AfterTest
    public void tearDown() {
        System.out.println();
        driver.close();
        logger.info("Closing driver...");

    }
}