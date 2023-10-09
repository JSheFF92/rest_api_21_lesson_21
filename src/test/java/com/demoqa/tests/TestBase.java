package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.demoqa.api.AuthorizationApi;
import com.demoqa.api.BooksApi;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    BooksApi booksApi = new BooksApi();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterAll
    static void afterAll() {
        Selenide.closeWebDriver();
    }
}
