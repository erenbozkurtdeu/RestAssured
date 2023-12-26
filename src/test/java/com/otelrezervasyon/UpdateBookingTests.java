package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest{
    @Test
    public void updateBookingTest(){
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token="+ createToken())
                .body(bookingObject("Ayşe", "Fatma", 456, false))
                .put("/booking/" + createBookingId());

        Assertions.assertEquals("Ayşe", response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Fatma", response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(456,(Integer) response.jsonPath().getJsonObject("totalprice"));
        Assertions.assertEquals(false, response.jsonPath().getJsonObject("depositpaid"));
    }
}