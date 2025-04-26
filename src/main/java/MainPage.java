import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение без комиссии')]")
    private WebElement onlinePaymentTitle;

    @FindBy(xpath = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement detailsLink;

    @FindBy(xpath = "//div[contains(@class, 'pay__partners')]//img")
    private List<WebElement> paymentSystemLogos;

    @FindBy(xpath = "//div[contains(@class, 'pay__cards')]//label")
    private List<WebElement> paymentOptions;

    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@placeholder='Номер договора']")
    private WebElement contractNumberInput;

    @FindBy(xpath = "//input[@placeholder='Номер карты']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    @FindBy(xpath = "//button[contains(text(), 'Принять и закрыть')]")
    private WebElement cookieAcceptButton;

    private static final String TEST_PHONE_NUMBER = "297777777";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Принять cookies если отображаются")
    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
            takeScreenshot();
        } catch (Exception e) {
            System.out.println("Cookie popup not found");
        }
    }

    @Step("Получить заголовок блока онлайн платежей")
    public String getOnlinePaymentTitle() {
        return wait.until(ExpectedConditions.visibilityOf(onlinePaymentTitle)).getText();
    }

    @Step("Получить количество логотипов платежных систем")
    public int getPaymentSystemLogosCount() {
        return paymentSystemLogos.size();
    }

    @Step("Выбрать опцию платежа: {optionName}")
    public void selectPaymentOption(String optionName) {
        paymentOptions.stream()
                .filter(option -> option.getText().contains(optionName))
                .findFirst()
                .ifPresent(WebElement::click);
        takeScreenshot();
    }

    @Step("Ввести тестовый номер телефона")
    public void enterTestPhoneNumber() {
        phoneInput.clear();
        phoneInput.sendKeys(TEST_PHONE_NUMBER);
        takeScreenshot();
    }

    @Step("Нажать кнопку 'Продолжить'")
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        takeScreenshot();
    }

    @Step("Получить URL ссылки 'Подробнее о сервисе'")
    public String getDetailsLinkHref() {
        return detailsLink.getAttribute("href");
    }

    @Step("Нажать ссылку 'Подробнее о сервисе'")
    public void clickDetailsLink() {
        detailsLink.click();
        takeScreenshot();
    }

    @Step("Получить плейсхолдер активного поля ввода")
    public String getActiveInputPlaceholder() {
        if (phoneInput.isDisplayed()) {
            return phoneInput.getAttribute("placeholder");
        } else if (contractNumberInput.isDisplayed()) {
            return contractNumberInput.getAttribute("placeholder");
        }
        return cardNumberInput.getAttribute("placeholder");
    }

    @Step("Получить тестовый номер телефона")
    public String getTestPhoneNumber() {
        return TEST_PHONE_NUMBER;
    }
}