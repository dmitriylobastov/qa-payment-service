package ru.stepup.payments.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy (css = ".inventory_item")
    private List<WebElement> inventoryItems;

    private final By addToCart = By.cssSelector(".btn_inventory");

    private final By cart = By.cssSelector(".shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getProductCount() {
        return inventoryItems.size();
    }

    public void addProductToCart() {
        click(addToCart);
    }

    public void goToCart() {
        click(cart);
    }
}
