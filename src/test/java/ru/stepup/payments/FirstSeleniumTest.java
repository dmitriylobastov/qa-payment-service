package ru.stepup.payments;

import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.stepup.payments.pages.*;

import static ru.stepup.payments.data.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstSeleniumTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverSingleton.getDriver("chrome");
    }

    @BeforeEach
    void setUp() {
        driver = WebDriverSingleton.getDriver("chrome");
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }

    @AfterAll
    static void tearDownAll() {
        WebDriverSingleton.quitDriver();
    }

    @Test
    void shouldOpenSauceDemo() {
        assertThat(driver.getTitle()).contains("Swag Labs");
    }

    @Test
    void shouldLoginSuccessfully() {
        new LoginPage(driver).login(STANDARD_USER, SECRET_SAUCE);
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.waitForUrlContains("inventory");
        assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    void shouldShowProductsAfterLogin() {
        new LoginPage(driver).login(STANDARD_USER, SECRET_SAUCE);
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.waitForUrlContains("inventory");
        assertThat(inventoryPage.getProductCount()).isEqualTo(6);
    }

    @Test
    void shouldAddProductToCart() {
        new LoginPage(driver).login(STANDARD_USER, SECRET_SAUCE);
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.waitForUrlContains("inventory");
        inventoryPage.addProductToCart();
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.waitForUrlContains("cart");
        assertThat(cartPage.getCartItemCount()).isEqualTo(1);
    }

    @Test
    void shouldCheckout() {
        new LoginPage(driver).login(STANDARD_USER, SECRET_SAUCE);
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.waitForUrlContains("inventory");
        inventoryPage.addProductToCart();
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.waitForUrlContains("cart");
        assertThat(cartPage.getCartItemCount()).isEqualTo(1);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.goToCheckout();
        checkoutPage.waitForUrlContains("checkout-step-one");
        checkoutPage.fillInfo("Ivan", "Ivanov", "123456");
        checkoutPage.clickContinue();
        checkoutPage.waitForUrlContains("checkout-step-two");
        assertThat(driver.getCurrentUrl()).contains("checkout-step-two");
    }
}
