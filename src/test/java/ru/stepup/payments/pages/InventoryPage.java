package ru.stepup.payments.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private final WebDriver driver;

    private By inventoryItems = By.cssSelector(".inventory_item");

    public InventoryPage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Объект драйвера пустой");
        }
        this.driver = driver;
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }
}
