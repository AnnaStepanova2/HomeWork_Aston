import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PaymentPage;

public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");

        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test(priority = 1)
    public void verifyOnlinePaymentBlockTitle() {
        Assert.assertEquals(mainPage.getOnlinePaymentTitle(),
                "Онлайн пополнение без комиссии",
                "Название блока не соответствует ожидаемому");
    }

    @Test(priority = 2)
    public void verifyPaymentSystemLogos() {
        Assert.assertTrue(mainPage.getPaymentSystemLogosCount() >= 4,
                "Количество логотипов платежных систем меньше ожидаемого");
    }

    @Test(priority = 3)
    public void verifyDetailsLink() {
        String expectedUrlPart = "/help/poryadok-oplaty-i-popolneniya-scheta/";
        Assert.assertTrue(mainPage.getDetailsLinkHref().contains(expectedUrlPart),
                "Ссылка 'Подробнее' ведет на неверный URL");
    }

    @Test(priority = 4)
    public void verifyPaymentOptionsPlaceholders() {
        String[][] testData = {
                {"Услуги связи", "Номер телефона"},
                {"Домашний интернет", "Номер договора"},
                {"Рассрочка", "Номер карты"},
                {"Задолженность", "Номер договора"}
        };

        for (String[] data : testData) {
            mainPage.selectPaymentOption(data[0]);
            Assert.assertEquals(mainPage.getActiveInputPlaceholder(), data[1],
                    String.format("Неверный плейсхолдер для '%s'", data[0]));
        }
    }

    @Test(priority = 5)
    public void testMobilePaymentProcess() {

        mainPage.selectPaymentOption("Услуги связи");


        mainPage.enterTestPhoneNumber();


        mainPage.clickContinueButton();


        PaymentPage paymentPage = new PaymentPage(driver);


        Assert.assertTrue(paymentPage.getDisplayedPhoneNumber().contains(mainPage.getTestPhoneNumber()),
                "Отображается неверный номер телефона");


        Assert.assertEquals(paymentPage.getAmountValue(), "0.00",
                "Сумма по умолчанию неверная");


        Assert.assertTrue(paymentPage.verifyCardFieldsPlaceholders(),
                "Плейсхолдеры полей карты не соответствуют ожидаемым");


        Assert.assertTrue(paymentPage.getPaymentSystemIconsCount() >= 3,
                "Недостаточно иконок платежных систем");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}