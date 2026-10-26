package ru.stepup.payments;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stepup.payments.pages.CartPage;
import ru.stepup.payments.pages.InventoryPage;
import ru.stepup.payments.pages.LoginPage;

import java.time.Duration;

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
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("inventory"));
        assertThat(driver.getCurrentUrl()).contains("inventory");
    }

    @Test
    void shouldShowProductsAfterLogin() {
        driver.get("https://www.saucedemo.com");
        new LoginPage(driver).login(STANDARD_USER, SECRET_SAUCE);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("inventory"));
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertThat(inventoryPage.getProductCount()).isEqualTo(6);
    }

    @Test
    void shouldAddProductToCart() {
        driver.get("https://www.saucedemo.com");
        new LoginPage(driver).login(STANDARD_USER, SECRET_SAUCE);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("inventory"));
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("cart"));
        assertThat(cartPage.getCartItemCount()).isEqualTo(1);
    }
}
