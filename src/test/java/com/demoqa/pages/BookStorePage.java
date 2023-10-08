package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BookStorePage {
    private final SelenideElement
            addBook = $(".rt-tbody");

    public BookStorePage addBook(String value) {
        addBook.$(byText(value)).click();

        return this;
    }
}
