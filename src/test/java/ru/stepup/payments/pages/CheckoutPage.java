package ru.stepup.payments.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By checkoutButton = By.id("checkout");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void goToCheckout() {
        click(checkoutButton);
    }

    public void fillInfo(String firstName, String lastName, String zip) {
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(postalCodeField, zip);
    }

    public void clickContinue() {
        click(continueButton);
    }


}
