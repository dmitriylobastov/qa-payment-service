package ru.stepup.payments;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    void shouldGetUser() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"));
    }

    @Test
    void shouldCreatePost() {
        given()
                .contentType("application/json")
                .body("{ \"title\": \"Мой новый пост\", \"body\": \"Это текст поста\", \"userId\": 1 }")
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("Мой новый пост"));
    }

    @Test
    void shouldCreatePostNew() {
        Response response = given()
                .contentType("application/json")
                .body("{ \"title\": \"Мой новый пост\", \"body\": \"Это текст поста\", \"userId\": 1 }")
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();
        int id = response.path("id");
        assertThat(id).isEqualTo(101);
        assertThat(response.header("Content-Type")).contains("application/json");
        assertThat(response.path("title").toString()).isEqualTo("Мой новый пост");
    }
}
