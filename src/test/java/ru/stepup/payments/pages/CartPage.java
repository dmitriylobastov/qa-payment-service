package ru.stepup.payments.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By cartItem = By.cssSelector(".cart_item");



    public CartPage(WebDriver driver){
        super(driver);
    }

    public int getCartItemCount(){
        return driver.findElements(cartItem).size();
    }

}
