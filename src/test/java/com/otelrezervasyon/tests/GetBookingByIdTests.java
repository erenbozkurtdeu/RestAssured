package com.otelrezervasyon.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest{

    @Test
    public void getBookingById(){
        Response response = given(spec)
                .when()
                .get("/booking/" + createBookingId());

        response
                .then()
                .statusCode(200);

        Assertions.assertEquals("Ali", response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Veli", response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(123,(Integer) response.jsonPath().getJsonObject("totalprice"));
    }
}
