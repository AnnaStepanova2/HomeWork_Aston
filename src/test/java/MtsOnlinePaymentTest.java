import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("MTS Online Payment")
@Feature("Проверка функционала онлайн платежей")
public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeClass(description = "Настройка тестового окружения")
    @Step("Инициализация драйвера и открытие сайта")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");

        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test(priority = 1)
    @Story("Проверка заголовка блока онлайн платежей")
    @Description("Проверка что заголовок блока соответствует ожидаемому")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyOnlinePaymentBlockTitle() {
        Assert.assertEquals(mainPage.getOnlinePaymentTitle(),
                "Онлайн пополнение без комиссии",
                "Название блока не соответствует ожидаемому");
    }

    @Test(priority = 2)
    @Story("Проверка логотипов платежных систем")
    @Description("Проверка количества отображаемых логотипов платежных систем")
    @Severity(SeverityLevel.NORMAL)
    public void verifyPaymentSystemLogos() {
        Assert.assertTrue(mainPage.getPaymentSystemLogosCount() >= 4,
                "Количество логотипов платежных систем меньше ожидаемого");
    }

    @Test(priority = 3)
    @Story("Проверка ссылки 'Подробнее'")
    @Description("Проверка корректности URL ссылки 'Подробнее о сервисе'")
    @Severity(SeverityLevel.MINOR)
    public void verifyDetailsLink() {
        String expectedUrlPart = "/help/poryadok-oplaty-i-popolneniya-scheta/";
        Assert.assertTrue(mainPage.getDetailsLinkHref().contains(expectedUrlPart),
                "Ссылка 'Подробнее' ведет на неверный URL");
    }

    @Test(priority = 4)
    @Story("Проверка плейсхолдеров для разных типов платежей")
    @Description("Проверка корректности плейсхолдеров для разных вариантов оплаты")
    @Severity(SeverityLevel.NORMAL)
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
    @Story("Процесс оплаты мобильной связи")
    @Description("Проверка полного процесса оплаты услуг мобильной связи")
    @Severity(SeverityLevel.BLOCKER)
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

    @AfterClass(description = "Завершение тестов")
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}