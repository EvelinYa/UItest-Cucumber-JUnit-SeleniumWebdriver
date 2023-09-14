package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage extends ConfigPage {
    public BookPage(WebDriver driver) {
        super(driver);
    }

    private final WebElement addToCartButton = driver.findElement(By.xpath("//div[@class='product-sidebar__ordering']//button[contains(text(),'До кошика')]"));
    private final WebElement buyButton = driver.findElement(By.xpath(" //div[@class='product-sidebar__ordering']//button[contains(text(),'Купити зараз')]"));

    public void addToCart() {
        addToCartButton.click();
    }

    public CheckoutPage buyBook() {
        buyButton.click();
        String expectedSearchUrl = "https://www.yakaboo.ua/ua/checkout";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains(expectedSearchUrl));
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.title.isDisplayed();
        return new CheckoutPage(driver);
    }
}