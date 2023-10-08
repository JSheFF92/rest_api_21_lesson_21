package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement
            goToStore = $("#gotoStore"),
            closeModal = $("#closeSmallModal-ok"),
            titleDelete = $("[title='Delete']");

    public ProfilePage openStore() {
        goToStore.click();

        return this;
    }

    public ProfilePage deleteButton() {
        titleDelete.click();

        return this;
    }

    public ProfilePage closeSmallModal() {
        closeModal.click();

        return this;
    }
}
