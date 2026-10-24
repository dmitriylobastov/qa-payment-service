package ru.stepup.payments.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage (WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Объект драйвера пустой");
        }
        this.driver = driver;
    }

    public void login(String user, String pass){
        driver.findElement(usernameField)
                .sendKeys(user);
        driver.findElement(passwordField)
                .sendKeys(pass);
        driver.findElement(loginButton)
                .click();

    }
}
