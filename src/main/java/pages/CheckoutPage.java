package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends ConfigPage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    protected WebElement title = driver.findElement(By.xpath("//h2[contains(text(),'Оформлення замовлення')]"));
    private final WebElement firstName = driver.findElement(By.name("first name"));
    private final WebElement lastName = driver.findElement(By.name("last name"));
    private final WebElement telNumber = driver.findElement(By.xpath("//input[@type='tel']"));
    private final WebElement email = driver.findElement(By.name("email"));
    private final WebElement city = driver.findElement(By.xpath("//input[@placeholder='Введіть назву міста...']"));
    By novaPoshtaNumber = By.xpath("//input[@placeholder='Адреса відділення']"); //div[contains(text(),'Відділення №11')]
    private final WebElement comment = driver.findElement(By.tagName("textarea"));
    protected WebElement submitButton = driver.findElement(By.xpath("//section[@class='order-submit sidebar']//button[contains(text(),'Підтвердити замовлення')]"));

    public void makeOrderUsingDefaultUserInfoForm(String addFirstName, String addLastName, String addTelNumber, String addEmail, String addCityName, String chooseCityFromList, String addNovaPoshtaNumber, String choosePostFromListXpath, String addComment) {
        firstName.sendKeys(addFirstName);
        lastName.sendKeys(addLastName);
        telNumber.sendKeys(addTelNumber);
        email.sendKeys(addEmail);
        addCity(addCityName, chooseCityFromList);
        addPostNumber(addNovaPoshtaNumber, choosePostFromListXpath);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(comment));
        comment.sendKeys(addComment);
    }

    public void confirmOrder(String expectedSearchUrl) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        By locator = By.xpath("//section[@class='order-submit sidebar']//div[@class='order-submit-inner disabled']");
        submitButton.click();
        wait.until(ExpectedConditions.urlContains(expectedSearchUrl));
    }

    public void addPostNumber(String addNovaPoshtaNumber, String choosePostFromListXpath) {
        WebElement PostNumber = driver.findElement(novaPoshtaNumber);
        PostNumber.sendKeys(addNovaPoshtaNumber);
        WebElement choosePost = driver.findElement(By.xpath("//div[contains(text(),'" + choosePostFromListXpath + "')]"));
        choosePost.click();
    }

    public void addCity(String addCityName, String chooseCityFromList) {
        city.sendKeys(addCityName);
        WebElement chooseCity = driver.findElement(By.xpath("//li[normalize-space()='" + chooseCityFromList + "']"));
        chooseCity.click();
    }

    public void booksQuantity(String quantity) {
        WebElement bookQuantity = driver.findElement(By.xpath("//div[@class='products-box sidebar']//span[contains(text(),'" + quantity + "')]"));
        bookQuantity.isDisplayed();
    }

    public void checkBookCheckoutInfo(String addBookTitle, String addBookAuthor, String addBookCode, String addBookPrice, String addCurrencyType, String addImgLink, String addBookType, String isBookAvailable, String addBookQuantity) {
        WebElement bookTitle = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//a[contains(text(),'" + addBookTitle + "')]"));
        bookTitle.isDisplayed();
        WebElement bookAuthor = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//span[contains(text(),'" + addBookAuthor + "')]"));
        bookAuthor.isDisplayed();
        WebElement bookCode = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//span[contains(text(),'" + addBookCode + "')]"));
        bookCode.isDisplayed();
        WebElement bookCodeLabel = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//span[contains(text(),'Код товару')]"));
        bookCodeLabel.isDisplayed();
        WebElement bookPrice = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//span[contains(text(),'" + addBookPrice + "')]"));
        bookPrice.isDisplayed();
        WebElement currencyType = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//span[contains(text(),'" + addCurrencyType + "')]"));
        currencyType.isDisplayed();
        WebElement bookImg = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//img[contains (@src,'" + addImgLink + "')]"));
        bookImg.isDisplayed();
        WebElement bookType = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//span[contains(text(),'" + addBookType + "')]"));
        bookType.isDisplayed();
        WebElement bookAvailable = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//span[contains(text(),'" + isBookAvailable + "')]"));
        bookAvailable.isDisplayed();
        WebElement bookQuantity = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//span[contains(text(),'" + addBookCode + "')]//ancestor::div[@class='checkout-product-card']//span[contains(text(),'" + addBookQuantity + "')]"));
        bookQuantity.isDisplayed();
    }

    public void totalPayForBooks(String totalPayBooks) {
        WebElement totalForBooks = driver.findElement(By.xpath("//div[@class='checkout-sidebar']//div[contains(text(),'" + totalPayBooks + "')]"));
    }

    public void totalPayForOrder(String totalPayForOrder, String addBooksQuantity, String totalPayForBooks, String totalPayForDelivery) {
        WebElement totalForOrder = driver.findElement(By.xpath("//section[@class='order-submit sidebar']//div[contains(text(),'" + totalPayForOrder + "')]"));
        totalForOrder.isDisplayed();
        WebElement totalForOrderLabel = driver.findElement(By.xpath("//section[@class='order-submit sidebar']//div[contains(text(),'До сплати')]"));
        totalForOrderLabel.isDisplayed();
        WebElement productsQuantityLabel = driver.findElement(By.xpath("//section[@class='order-submit sidebar']//div[contains(text(),'" + addBooksQuantity + "')]"));
        productsQuantityLabel.isDisplayed();
        WebElement totalForBooks = driver.findElement(By.xpath("//section[@class='order-submit sidebar']//div[contains(text(),'" + totalPayForBooks + "')]"));
        totalForBooks.isDisplayed();
        WebElement totalForDelivery = driver.findElement(By.xpath("//section[@class='order-submit sidebar']//div[contains(text(),'" + totalPayForDelivery + "')]"));
        totalForDelivery.isDisplayed();
        WebElement deliveryLabel = driver.findElement(By.xpath("//section[@class='order-submit sidebar']//div[contains(text(),'Доставка')]"));
        deliveryLabel.isDisplayed();

    }
}