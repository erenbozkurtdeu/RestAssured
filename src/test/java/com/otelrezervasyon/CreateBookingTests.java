package com.otelrezervasyon;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTests extends BaseTest{
    @Test
    public void createBookingTest(){
        Response response = createBooking();

        Assertions.assertEquals("Ali", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Veli", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(123, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
    }
}