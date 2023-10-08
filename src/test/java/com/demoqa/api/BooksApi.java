package com.demoqa.api;


import static com.demoqa.Spec.BookSpecs.*;

import com.demoqa.models.AddBooksListModel;
import com.demoqa.models.DeleteResponseBookModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.models.DeleteOneBookModel;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooksApi {
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(successDeleteBook204ResponseSpec);
    }

    public void addBook(LoginResponseModel loginResponse, AddBooksListModel booksList) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(successAddBook201ResponseSpec);
    }

    public void deleteOneBook(LoginResponseModel loginResponse, DeleteOneBookModel deleteBookModel) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .body(deleteBookModel)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(successDeleteBook204ResponseSpec)
                .statusCode(204);
    }

    public DeleteResponseBookModel deleteBookWithWrongIsbn(String userId, String token, String isbnWrong) {
        DeleteOneBookModel bookForDeleteData = new DeleteOneBookModel();
        bookForDeleteData.setUserId(userId);
        bookForDeleteData.setIsbn(isbnWrong);

        return given(bookRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookForDeleteData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(errorDeleteBook400ResponseSpec)
                .extract().as(DeleteResponseBookModel.class);
    }

    public void assertEqualsErrorCode(DeleteResponseBookModel response, String code, String message) {
        assertEquals(code, response.getCode());
        assertEquals(message, response.getMessage());
    }

}