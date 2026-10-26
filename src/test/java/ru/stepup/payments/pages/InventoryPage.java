package ru.stepup.payments.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By inventoryItems = By.cssSelector(".inventory_item");

    private final By addToCart = By.cssSelector(".btn_inventory");

    private final By cart = By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }

    public void addFirstProductToCart (){
        driver.findElements(addToCart).get(0).click();
    }

    public void goToCart(){
        driver.findElement(cart).click();
    }
}
