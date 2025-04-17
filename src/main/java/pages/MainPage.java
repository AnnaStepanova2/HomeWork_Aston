package pages;

import org.openqa.selenium.By;
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

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (Exception e) {
            System.out.println("Cookie popup not found");
        }
    }

    public String getOnlinePaymentTitle() {
        return wait.until(ExpectedConditions.visibilityOf(onlinePaymentTitle)).getText();
    }

    public int getPaymentSystemLogosCount() {
        return paymentSystemLogos.size();
    }

    public void selectPaymentOption(String optionName) {
        paymentOptions.stream()
                .filter(option -> option.getText().contains(optionName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void enterTestPhoneNumber() {
        phoneInput.clear();
        phoneInput.sendKeys(TEST_PHONE_NUMBER);
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getDetailsLinkHref() {
        return detailsLink.getAttribute("href");
    }

    public void clickDetailsLink() {
        detailsLink.click();
    }

    public String getActiveInputPlaceholder() {
        if (phoneInput.isDisplayed()) {
            return phoneInput.getAttribute("placeholder");
        } else if (contractNumberInput.isDisplayed()) {
            return contractNumberInput.getAttribute("placeholder");
        }
        return cardNumberInput.getAttribute("placeholder");
    }

    public String getTestPhoneNumber() {
        return TEST_PHONE_NUMBER;
    }
}