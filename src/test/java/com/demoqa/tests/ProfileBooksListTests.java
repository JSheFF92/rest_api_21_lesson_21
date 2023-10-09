package com.demoqa.tests;

import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.DeleteOneBookModel;
import com.demoqa.models.IsbnModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.pages.FavIconPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demoqa.tests.TestData.credentials;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ProfileBooksListTests extends TestBaseRemote {

    FavIconPage fivIconPage = new FavIconPage();

    @Test
    void deleteBookFromProfileTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        DeleteOneBookModel deleteOneBookModel = new DeleteOneBookModel();
        deleteOneBookModel.setIsbn("9781449325862");
        deleteOneBookModel.setUserId(loginResponse.getUserId());

        booksApi.deleteOneBook(loginResponse, deleteOneBookModel);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        open("/profile");
        $("[id='see-book-Git Pocket Guide']").shouldNotBe(visible);

    }

    @Test
    void deleteOneBookTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449325862");
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBooksListModel booksList = new AddBooksListModel();
        booksList.setUserId(loginResponse.getUserId());
        booksList.setCollectionOfIsbns(isbnList);

        DeleteOneBookModel deleteOneBookModel = new DeleteOneBookModel();
        deleteOneBookModel.setIsbn("9781449325862");
        deleteOneBookModel.setUserId(loginResponse.getUserId());

        booksApi.deleteOneBook(loginResponse, deleteOneBookModel);

        fivIconPage.openIconPage(loginResponse,"userID","token","expires");

        open("/profile");
        $("[id='see-book-Git Pocket Guide']").shouldNot(visible);

    }
}