package com.demoqa.spec;


import com.demoqa.tests.TestBaseRemote;
import io.restassured.builder.ResponseSpecBuilder;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.demoqa.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class BookSpecs extends TestBaseRemote {
    public static RequestSpecification bookRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .log().body()
            .contentType(JSON)
            .baseUri(baseURI);

    public static ResponseSpecification successAddBook201ResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();


    public static ResponseSpecification successDeleteBook204ResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification errorDeleteBook400ResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .build();
}
