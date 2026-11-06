package ru.stepup.payments;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    private static WebDriver driver;

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            driver = DriverFactory.getNameBrowser(browser);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
