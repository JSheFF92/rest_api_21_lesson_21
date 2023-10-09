//package com.demoqa.tests;
//
//import com.codeborne.selenide.Selenide;
//import com.demoqa.models.*;
//import com.demoqa.pages.FavIconPage;
//import com.demoqa.pages.LoginPage;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.Cookie;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.codeborne.selenide.Condition.visible;
//import static com.codeborne.selenide.Selectors.byText;
//import static com.codeborne.selenide.Selenide.*;
//import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
//import static com.demoqa.tests.TestData.*;
//import static io.qameta.allure.Allure.step;
//import static io.restassured.RestAssured.given;
//import static io.restassured.http.ContentType.JSON;
//
//public class ProfileBooksListTests extends TestBase {
//
//    LoginPage loginPage = new LoginPage();
//    FavIconPage fivIconPage = new FavIconPage();
//
//    @Test
//    void deleteBookFromProfileTest() {
//        LoginResponseModel loginResponse = authorizationApi.login(credentials);
//        booksApi.deleteAllBooks(loginResponse);
//
//        IsbnModel isbnModel = new IsbnModel();
//        isbnModel.setIsbn("9781449325862");
//        List<IsbnModel> isbnList = new ArrayList<>();
//        isbnList.add(isbnModel);
//
//        AddBooksListModel booksList = new AddBooksListModel();
//        booksList.setUserId(loginResponse.getUserId());
//        booksList.setCollectionOfIsbns(isbnList);
//
//        DeleteOneBookModel deleteOneBookModel = new DeleteOneBookModel();
//        deleteOneBookModel.setIsbn("9781449325862");
//        deleteOneBookModel.setUserId(loginResponse.getUserId());
//
//        booksApi.deleteOneBook(loginResponse, deleteOneBookModel);
//
//        open("/favicon.ico");
//        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
//        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
//        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
//
//        open("/profile");
//        $("[id='see-book-Git Pocket Guide']").shouldNotBe(visible);
//
//    }
//
//    @Test
//    void deleteBookUiAndApiFromProfileTest() {
//
////        open("/login");
////
////        $("#userName").setValue(login);
////        $("#password").setValue(password);
////        $("#login").click();
//
//        step("Open main page", () -> {
//            loginPage
//                    .openPage()
//                    .settingsRegistrationPage()
//                    .addCredential(login, password);
//        });
//
//        //тут я в профиле нужна страница профиля профильпейдж
//        $("#gotoStore").click();
//        //bookStorePage
//        $(".rt-tbody").$(byText("Git Pocket Guide")).click();
//        //bookPage
//        executeJavaScript("$('#fixedban').remove()");
//        executeJavaScript("$('footer').remove()");
//        $(".text-right.fullButton").click();
//        //тут нужно нажать "да" в выпадающем окне
//        Selenide.switchTo().alert().accept();
//        $(".left-pannel").$(byText("Profile")).click();
//
//        //profilePage
//        $("[title='Delete']").click();
//        $("#closeSmallModal-ok").click();
//
//        LoginResponseModel loginResponse = authorizationApi.login(credentials);
//        DeleteResponseBookModel response = booksApi.deleteBookWithWrongIsbn(loginResponse.getUserId(), loginResponse.getToken(), "9781449325862");
//        booksApi.assertEqualsErrorCode(response, "1206", "ISBN supplied is not available in User's Collection!");
//    }
//    @Test
//    void deleteOneBookTest() {
//        LoginResponseModel loginResponse = authorizationApi.login(credentials);
//        booksApi.deleteAllBooks(loginResponse);
//
//        IsbnModel isbnModel = new IsbnModel();
//        isbnModel.setIsbn("9781449325862");
//        List<IsbnModel> isbnList = new ArrayList<>();
//        isbnList.add(isbnModel);
//
//        AddBooksListModel booksList = new AddBooksListModel();
//        booksList.setUserId(loginResponse.getUserId());
//        booksList.setCollectionOfIsbns(isbnList);
//
//        DeleteOneBookModel deleteOneBookModel = new DeleteOneBookModel();
//        deleteOneBookModel.setIsbn("9781449325862");
//        deleteOneBookModel.setUserId(loginResponse.getUserId());
//
//        booksApi.deleteOneBook(loginResponse, deleteOneBookModel);
//
//        fivIconPage.openIconPage(loginResponse,"userID","token","expires");
//
//        open("/profile");
//        $("[id='see-book-Git Pocket Guide']").shouldNot(visible);
//
//    }
//
//    @Test
//    void deleteBook() {
//        LoginResponseModel loginResponse = authorizationApi.login(credentials);
//        String authData = "{ \"userName\": \"testUser1\", \"password\": \"testUser1@\" }"; // BAD PRACTICE
//        Response auth = given()
//                .log().uri()
//                .log().method()
//                .log().body()
//                .contentType(JSON)
//                .body(authData)
//                .when()
//                .post("/Account/v1/Login")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .extract().response();
//
//
//        given()
//                .contentType(JSON)
//                .header("Authorization", "Bearer " + auth.path("token"))
//                .queryParam("UserId", "76107d76-4721-4cc7-a1a5-8bad2360a0b6")
//                .when()
//                .delete("/BookStore/v1/Books")
//                .then()
//                .statusCode(204);
//
//        IsbnModel isbnModel = new IsbnModel();
//        isbnModel.setIsbn("9781449325862");
//        List<IsbnModel> isbnList = new ArrayList<>();
//        isbnList.add(isbnModel);
//
//        AddBooksListModel booksList = new AddBooksListModel();
////        booksList.setUserId(auth.path("userId"));
//        booksList.setUserId(loginResponse.getUserId());
//        booksList.setCollectionOfIsbns(isbnList);
//
//
//        given()
//                .contentType(JSON)
////                .header("Authorization", "Bearer " + auth.path("token"))
//                .header("Authorization", "Bearer " + loginResponse.getToken())
//                .body(booksList)
//                .when()
//                .post("/BookStore/v1/Books")
//                .then()
//                .statusCode(201);
//
//
//        open("/favicon.ico");
//        getWebDriver().manage().addCookie(new Cookie("userID", auth.path("userId")));
//        getWebDriver().manage().addCookie(new Cookie("token", auth.path("token")));
//        getWebDriver().manage().addCookie(new Cookie("expires", auth.path("expires")));
//
//        open("/profile");
//        $("[id='see-book-Git Pocket Guide']").shouldBe(visible);
//
//        given()
//                .contentType(JSON)
//                .header("Authorization", "Bearer " + loginResponse.getToken())
//                .queryParam("UserId", loginResponse.getUserId())
//                .when()
//                .delete("/BookStore/v1/Books")
//                .then()
//                .statusCode(204);
//
//        open("/profile");
//        $("[id='see-book-Git Pocket Guide']").shouldNot(visible);
//    }
//}