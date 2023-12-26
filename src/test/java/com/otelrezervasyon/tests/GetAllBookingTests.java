package com.otelrezervasyon.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingTests extends BaseTest{

    @Test
    public void getAllBookingTest(){
        given(spec)
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200);
    }

    @Test
    public void getBookings_with_firstname_filter_test(){
        int bookingId = createBookingId();

        spec.queryParam("firstname", "Ali");
        spec.queryParam("lastname", "Veli");

        Response response = given(spec)
                .when()
                .get("/booking");

        response
                .then()
                .statusCode(200);

        List<Integer> filterlist = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filterlist.contains(bookingId));
    }
}