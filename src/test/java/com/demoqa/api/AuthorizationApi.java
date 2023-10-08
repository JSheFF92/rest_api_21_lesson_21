package com.demoqa.api;

import com.demoqa.models.CredentialsModel;
import com.demoqa.models.LoginResponseModel;

import static com.demoqa.Spec.LoginSpec.LoginRequestSpec;
import static com.demoqa.Spec.LoginSpec.successAuthSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public LoginResponseModel login(CredentialsModel credentials){
        return given(LoginRequestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(successAuthSpec)
                .extract().as(LoginResponseModel.class);
    }
}
