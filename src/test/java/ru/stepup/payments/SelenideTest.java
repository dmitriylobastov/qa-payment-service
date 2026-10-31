package ru.stepup.payments;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @Test
    void shouldLogin() {
        open("https://www.saucedemo.com/");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce");
        $("#login-button").click();
        assertThat(url()).contains("inventory");
    }
}
