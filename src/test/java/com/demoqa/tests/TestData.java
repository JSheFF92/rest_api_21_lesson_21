package com.demoqa.tests;

import com.demoqa.models.CredentialsModel;

public class TestData {

    private static String login = "testUser1",
            password = "testUser1@";

    public static CredentialsModel credentials = new CredentialsModel(login, password);
}