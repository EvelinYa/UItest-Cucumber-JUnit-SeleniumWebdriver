package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import java.util.concurrent.TimeUnit;

public class CriticalPaths {
    private static WebDriver driver;
    private final static String baseUrl = "https://www.yakaboo.ua";

    @Given("I am on the Yakaboo website")
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @When("I search for \"Емоційний інтелект\" Product")
    public void searchForProduct() {
        HomePage homePage = new HomePage(driver);
        homePage.fillSearchItemInput("Емоційний інтелект");
    }

    @And("I select \"Емоційний інтелект\" Product from the search results")
    public void chooseProduct() {
        HomePage homePage = new HomePage(driver);
        homePage.chooseBookFromSearchList("2");
    }

    @And("I add the Product to the Cart and proceed to the Cart")
    public void addProductToCart() {
        BookPage bookPage = new BookPage(driver);
        bookPage.addToCart();
        HomePage homePage = new HomePage(driver);
        homePage.cartCounter("1");
        homePage.bookIsAddedMessageClickOK();
        homePage.navigateCart();
    }

    @And("I check the Product in the Cart and go to the Checkout page")
    public void productInTheCart() {
        CartPage cartPage = new CartPage(driver);
        cartPage.cartBooksList(1);
        cartPage.booksQuantity("1 шт.");
        cartPage.addedBookIsDisplayed(
                "Емоційний інтелект",
                "Денiел Ґоулман",
                "850662",
                "331",
                "грн",
                "https://static.yakaboo.ua/media/catalog/product/i/m/img651_cr_3.png",
                "Паперова",
                "В наявності");
        cartPage.removeButtonDisplay("850662");
        cartPage.navigateCheckOutPage();
    }

    @And("I add all the Checkout information and Submit the Order")
    public void addAllCheckoutInfo() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.booksQuantity("1 товар у кошику");
        checkoutPage.checkBookCheckoutInfo(
                "Емоційний інтелект",
                "Денiел Ґоулман",
                "850662",
                "331",
                "грн",
                "https://static.yakaboo.ua/media/catalog/product/i/m/img651_cr_3.png",
                "Паперова",
                "В наявності",
                "1 шт.");
        checkoutPage.makeOrderUsingDefaultUserInfoForm(
                "FFFFFF",
                "LLLLLL",
                "0633018495",
                "eeeeee@gmail.com",
                "Тернопіль",
                "Тернопіль",
                "11",
                "№11",
                "Comment on Order");
        checkoutPage.totalPayForBooks("Разом 331 грн");
        checkoutPage.totalPayForOrder(
                "391 грн",
                "1 товар",
                "331 грн",
                "60 грн"
        );
        checkoutPage.confirmOrder("https://mapi.xpaydirect.com");
    }

    @And("I add all the Payment information and Pay for the Order")
    public void addPaymentInfoAndPay() {
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.addCardInfoAndPay(
                "4144 3453 5653 4535",
                "07",
                "23",
                "111");
    }

    @Then("I should see Order Confirmation Page")
    public void orderIsPayed() {
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.unsuccessfulPayment("СПРОБУЙТЕ ІНШУ КАРТКУ");
        driver.quit();
    }
}