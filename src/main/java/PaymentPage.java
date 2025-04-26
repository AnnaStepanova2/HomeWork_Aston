import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PaymentPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Сумма']")
    private WebElement amountInput;

    @FindBy(xpath = "//div[contains(@class, 'pay__card-info')]")
    private WebElement paymentInfo;

    @FindBy(xpath = "//button[contains(@class, 'pay__submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@placeholder='Номер карты']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[@placeholder='Срок действия']")
    private WebElement cardExpiryInput;

    @FindBy(xpath = "//input[@placeholder='Имя владельца']")
    private WebElement cardHolderInput;

    @FindBy(xpath = "//input[@placeholder='CVC/CVV']")
    private WebElement cardCvvInput;

    @FindBy(xpath = "//div[contains(@class, 'pay__cards')]//img")
    private List<WebElement> paymentSystemIcons;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить отображаемый номер телефона")
    public String getDisplayedPhoneNumber() {
        return wait.until(ExpectedConditions.visibilityOf(paymentInfo))
                .findElement(By.xpath(".//div[contains(@class, 'pay__card-number')]"))
                .getText();
    }

    @Step("Получить значение суммы платежа")
    public String getAmountValue() {
        return amountInput.getAttribute("value");
    }

    @Step("Получить текст кнопки оплаты")
    public String getPayButtonText() {
        return submitButton.getText();
    }

    @Step("Проверить плейсхолдеры полей карты")
    public boolean verifyCardFieldsPlaceholders() {
        takeScreenshot();
        return cardNumberInput.getAttribute("placeholder").equals("Номер карты") &&
                cardExpiryInput.getAttribute("placeholder").equals("Срок действия") &&
                cardHolderInput.getAttribute("placeholder").equals("Имя владельца") &&
                cardCvvInput.getAttribute("placeholder").equals("CVC/CVV");
    }

    @Step("Получить количество иконок платежных систем")
    public int getPaymentSystemIconsCount() {
        return paymentSystemIcons.size();
    }

    @Step("Проверить что все иконки платежных систем отображаются")
    public boolean arePaymentSystemIconsDisplayed() {
        takeScreenshot();
        return paymentSystemIcons.stream().allMatch(WebElement::isDisplayed);
    }
}