package com.otelrezervasyon.tests;

import com.otelrezervasyon.models.Booking;
import com.otelrezervasyon.models.BookingDates;
import com.otelrezervasyon.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTest{
    @Test
    public void createBookingTest(){
        Response response = createBooking();

        Assertions.assertEquals("Ali", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Veli", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(123, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
    }

    @Test
    public void createBookingWithPojo(){
        BookingDates bookingDates = new BookingDates("2023-01-01", "2023-01-02");
        Booking booking = new Booking("Mehmet", "Yilmaz", 500, false, bookingDates, "Breakfast");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.println(bookingResponse + " Booking response kaydedildi.");

        Assertions.assertEquals("Mehmet", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Yilmaz", bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("Breakfast", bookingResponse.getBooking().getAdditionalneeds());
    }
}