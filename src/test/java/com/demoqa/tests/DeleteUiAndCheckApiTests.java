package com.demoqa.tests;

import com.demoqa.models.*;
import com.demoqa.pages.BookPage;
import com.demoqa.pages.BookStorePage;
import com.demoqa.pages.LoginPage;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.Test;
import static com.demoqa.tests.TestData.*;
import static io.qameta.allure.Allure.step;

public class DeleteUiAndCheckApiTests extends TestBaseRemote{
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();
    BookStorePage bookStorePage = new BookStorePage();
    BookPage bookPage = new BookPage();

    @Test
    void deleteBookUiAndApiFromProfileTest() {

        step("Авторизация", () -> {
            loginPage
                    .openPage()
                    .settingsRegistrationPage()
                    .addCredential(login, password);
        });

        step("Переход в профиль", () ->
            profilePage
                    .openStore()
        );

        step("Переход в магазин книг", () ->
            bookStorePage
                    .addBook("Git Pocket Guide")
        );

        step("Добавляем книгу в коллекцию", () -> {
            bookPage
                    .addBookToCollection()
                    .modalClick("Profile");
        });

        step("Удаляем книгу", () -> {
            profilePage
                    .deleteButton()
                    .closeSmallModal();
        });

        step("Проверяем удаление книги через АПИ", () -> {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);
        DeleteResponseBookModel response = booksApi.deleteBookWithWrongIsbn(loginResponse.getUserId(), loginResponse.getToken(), "9781449325862");
        booksApi.assertEqualsErrorCode(response, "1206", "ISBN supplied is not available in User's Collection!");
        });
        }
}
