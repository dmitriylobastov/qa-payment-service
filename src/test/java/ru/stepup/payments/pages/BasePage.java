package ru.stepup.payments.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        validateDriver(driver);
        this.driver = driver;
    }

    private void validateDriver(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Объект драйвера пустой");
        }
    }
}
