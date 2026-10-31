package ru.stepup.payments;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stepup.payments.pages.CartPage;
import ru.stepup.payments.pages.CheckoutPage;
import ru.stepup.payments.pages.InventoryPage;
import ru.stepup.payments.pages.LoginPage;

import static ru.stepup.payments.data.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com/");
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        driver.quit();
    }

    @Test
    void shouldOpenSauceDemo() {
        driver.get("https://www.saucedemo.com/");
        assertThat(driver.getTitle()).contains("Swag Labs");
    }

    @Test
    void shouldLoginSuccessfully() {
        driver.get("https://www.saucedemo.com/");
        new LoginPage(driver).login(STANDARD_USER, SECRET_SAUCE);
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.waitForUrlContains("inventory");
        assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    void shouldShowProductsAfterLogin() {
        driver.get("https://www.saucedemo.com");
        new LoginPage(driver).login(STANDARD_USER, SECRET_SAUCE);
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.waitForUrlContains("inventory");
        assertThat(inventoryPage.getProductCount()).isEqualTo(6);
    }

    @Test
    void shouldAddProductToCart() {
        driver.get("https://www.saucedemo.com");
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
        driver.get("https://www.saucedemo.com");
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
