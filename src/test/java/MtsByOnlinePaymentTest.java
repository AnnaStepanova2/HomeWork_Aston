import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class MtsByOnlinePaymentTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String SITE_URL = "https://www.mts.by/";
    private static final String PHONE_NUMBER = "297777777";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(SITE_URL);


        try {
            WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Принять и закрыть')]")));
            cookieAccept.click();
        } catch (Exception e) {
            System.out.println("Cookie popup not found or not clickable");
        }
    }

    @Test(priority = 1)
    public void testBlockTitle() {
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Онлайн пополнение без комиссии')]")));

        Assert.assertTrue(blockTitle.isDisplayed(), "Block title is not displayed");
        Assert.assertEquals(blockTitle.getText(), "Онлайн пополнение без комиссии",
                "Block title text is incorrect");
    }

    @Test(priority = 2)
    public void testPaymentSystemsLogos() {
        List<WebElement> paymentLogos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'pay__partners')]//img")));

        Assert.assertTrue(paymentLogos.size() >= 4, "Expected at least 4 payment system logos");

        for (WebElement logo : paymentLogos) {
            Assert.assertTrue(logo.isDisplayed(), "Payment logo is not displayed");
            String src = logo.getAttribute("src");
            Assert.assertNotNull(src, "Logo image source is empty");
            Assert.assertFalse(src.isEmpty(), "Logo image source is empty");
        }
    }

    @Test(priority = 3)
    public void testDetailsLink() {
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")));

        Assert.assertTrue(detailsLink.isDisplayed(), "Details link is not displayed");

        String expectedHref = "/help/poryadok-oplaty-i-popolneniya-scheta/";
        String actualHref = detailsLink.getAttribute("href");
        Assert.assertTrue(actualHref.contains(expectedHref), "Details link href is incorrect");


        String originalWindow = driver.getWindowHandle();
        detailsLink.click();


        wait.until(ExpectedConditions.numberOfWindowsToBe(2));


        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }


        wait.until(ExpectedConditions.urlContains(expectedHref));
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedHref),
                "Details link leads to wrong page");


        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test(priority = 4)
    public void testContinueButtonFunctionality() {

        WebElement serviceType = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'pay__cards')]//label[contains(., 'Услуги связи')]")));
        serviceType.click();


        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Номер телефона']")));
        phoneInput.clear();
        phoneInput.sendKeys(PHONE_NUMBER);


        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Продолжить')]")));
        continueButton.click();


        WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Сумма']")));

        Assert.assertTrue(amountInput.isDisplayed(), "Amount input is not displayed after clicking Continue");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}