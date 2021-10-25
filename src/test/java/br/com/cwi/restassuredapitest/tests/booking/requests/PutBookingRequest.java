package br.com.cwi.restassuredapitest.tests.booking.requests;

import br.com.cwi.restassuredapitest.tests.booking.requests.payloads.BookingPayloads;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    public Response updateBookingToken(int id, String token){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.payloadValidUpdateBooking().toString())
                .put("booking/"+ id);
    }
}