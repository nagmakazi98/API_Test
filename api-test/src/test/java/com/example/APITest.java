package com.example;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class APITest {
    @Test
    public void createUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\" }";

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("John Doe"))
            .body("email", equalTo("john@example.com"))
            .log().all();
    }
    @Test
    public void getUser() {
    RestAssured.baseURI = "https://reqres.in/api";

    given()
        .header("Content-Type", "application/json")
    .when()
        .get("/users?page=2")
    .then()
        .statusCode(200)
        .body("data[4].first_name", equalTo("George"))
         .body("data[4].email", equalTo("george.edwards@reqres.in"))
         .body("data[4].last_name", equalTo("Edwards"))
         .body("data.first_name", hasItems("George", "Rachel"))
         .log().all();

}
}
