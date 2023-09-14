package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage extends ConfigPage {
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    private final WebElement cardNumber = driver.findElement(By.id("create[n]"));
    private final WebElement cardExpMonth = driver.findElement(By.id("create[m]"));
    private final WebElement cardExpYear = driver.findElement(By.id("create[y]"));
    private final WebElement cvvCode = driver.findElement(By.id("create[c]"));
    private final WebElement payButton = driver.findElement(By.xpath("//button[@type='submit']"));

    public void addCardInfoAndPay(String addCardNumber, String addCardExpMonth, String addCardExpYear, String addCVVCode) {
        cardNumber.sendKeys(addCardNumber);
        cardExpMonth.sendKeys(addCardExpMonth);
        cardExpYear.sendKeys(addCardExpYear);
        cvvCode.sendKeys(addCVVCode);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedConditions.elementToBeClickable(payButton)));
    }

    public void unsuccessfulPayment(String errorMessage) {
        payButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement unsuccessful = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Помилка операції')]")));
        wait.until(ExpectedConditions.visibilityOf(unsuccessful));
        Assertions.assertTrue(unsuccessful.isDisplayed());
        WebElement message = driver.findElement(By.xpath("//div[@class='failed-panel']/following-sibling::div"));
        message.isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(message));
        Assertions.assertEquals(errorMessage, message.getText());
    }
}