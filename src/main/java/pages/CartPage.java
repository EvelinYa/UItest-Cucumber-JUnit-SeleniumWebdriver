package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends ConfigPage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    protected WebElement cartTitle = driver.findElement(By.xpath("//div[@class='header']/span[text()='Кошик']"));
    private final WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Перейти до оформлення замовлення')]"));
    List<WebElement> cartItems = driver.findElements(By.xpath("//ul[@class='products custom-scroll']/div"));

    public void cartBooksList(int entityCount) {
        Assertions.assertEquals(entityCount, cartItems.size());
    }

    public void booksQuantity(String requiredQuantity) {
        WebElement quantity = driver.findElement(By.xpath("//span[@class='header-quantity']"));
        Assertions.assertEquals(requiredQuantity, quantity.getText());
    }

    public void addedBookIsDisplayed(String addBookTitle, String addBookAuthor, String addBookCode, String addBookPrice, String addCurrencyType, String addImgLink, String addBookType, String isBookAvailable) {
        WebElement bookCode = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]"));
        bookCode.isDisplayed();
        WebElement bookCodeLabel = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card product-cart']//span[contains(text(),'Код товару')]"));
        bookCodeLabel.isDisplayed();
        WebElement bookTitle = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='product-details']//a[contains(text(),'" + addBookTitle + "')]"));
        bookTitle.isDisplayed();
        WebElement bookAuthor = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='product-details']//span[contains(text(),'" + addBookAuthor + "')]"));
        bookAuthor.isDisplayed();
        WebElement bookPrice = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='product-details']//span[contains(text(),'" + addBookPrice + "')]"));
        bookPrice.isDisplayed();
        WebElement currencyType = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='product-details']//span[contains(text(),'" + addCurrencyType + "')]"));
        currencyType.isDisplayed();
        WebElement bookType = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='product-details']//span[contains(text(),'" + addBookType + "')]"));
        bookType.isDisplayed();
        WebElement bookImg = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card product-cart']//img[contains(@src, '" + addImgLink + "')]"));
        bookImg.isDisplayed();
        WebElement bookAvailable = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='product-details']//span[contains(text(),'" + isBookAvailable + "')]"));
        bookAvailable.isDisplayed();
    }

    public void removeButtonDisplay(String addBookCode) {
        WebElement removeButton = driver.findElement(By.xpath("//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card product-cart']//span[contains(text(),'Видалити')]"));
        removeButton.isDisplayed();
    }

    public CheckoutPage navigateCheckOutPage() {
        checkoutButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String expectedSearchUrl = "https://www.yakaboo.ua/ua/checkout";
        wait.until(ExpectedConditions.urlToBe(expectedSearchUrl));
        Assertions.assertTrue(driver.getCurrentUrl().contains(expectedSearchUrl));
        CheckoutPage checkout = new CheckoutPage(driver);
        wait.until(ExpectedConditions.visibilityOf(checkout.title));
        return new CheckoutPage(driver);
    }

}
