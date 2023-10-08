package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement
            userName = $("#userName"),
            userPassword = $("#password"),
            entranceProfile = $("#login");

    public LoginPage openPage() {
        open("/login");

        return this;
    }

    public LoginPage settingsRegistrationPage() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public LoginPage addCredential(String login, String password) {
        userName.setValue(login);
        userPassword.setValue(password);
        entranceProfile.click();

        return this;
    }



}
