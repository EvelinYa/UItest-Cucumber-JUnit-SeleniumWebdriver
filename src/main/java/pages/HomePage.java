package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends ConfigPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final WebElement searchInput = driver.findElement(By.id("search"));
    private final WebElement searchButton = driver.findElement(By.xpath("//input[@id='search']//following-sibling::button"));
    private final WebElement cartButton = driver.findElement(By.xpath("//button[@class='ui-btn-shopping-cart']"));
    By buyButtonLocator = By.xpath("//button[contains(text(),'Оформити замовлення')]");
    By okButtonLocator = By.xpath("//button[contains(text(),'OK')]");

    public void fillSearchItemInput(String productName) {
        searchInput.sendKeys(productName);
    }

    public BookPage chooseBookFromSearchList(String bookPositionInListOfSearchResult) {
        WebElement chooseBook = driver.findElement(By.xpath("//div[@class='ui-search-list']/div[" + bookPositionInListOfSearchResult + "]"));
        chooseBook.click();
        return new BookPage(driver);
    }

    public CartPage navigateCart() {
        cartButton.click();
        CartPage cartPage = new CartPage(driver);
        Assertions.assertTrue(cartPage.cartTitle.isDisplayed());
        return new CartPage(driver);
    }

    public void addToCartMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Товар додано до кошика!')]")));
        successMessage.isDisplayed();
        WebElement closeButton = driver.findElement(By.xpath("//div[contains(text(),'Товар додано до кошика!')]//preceding-sibling::img"));
        closeButton.isDisplayed();
        WebElement buyButton = driver.findElement(buyButtonLocator);
        buyButton.isDisplayed();
        WebElement okButton = driver.findElement(okButtonLocator);
        okButton.isDisplayed();
    }

    public void bookIsAddedMessageClickOK() {
        addToCartMessageDisplayed();
        WebElement okButton = driver.findElement(okButtonLocator);
        okButton.click();
    }

    public void cartCounter(String counterValue) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement counter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-btn-shopping-cart__counter']")));
        Assertions.assertEquals(counterValue, counter.getText());
    }
}