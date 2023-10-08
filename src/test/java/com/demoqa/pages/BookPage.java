package com.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BookPage {
    private final SelenideElement
            addToCollection = $(".text-right.fullButton"),
            modalAccept=$(".left-pannel");

    public BookPage addBookToCollection() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        addToCollection.click();

        return this;
    }

    public BookPage modalClick(String value) {
        Selenide.switchTo().alert().accept();
        modalAccept.$(byText(value)).click();

        return this;
    }
}
