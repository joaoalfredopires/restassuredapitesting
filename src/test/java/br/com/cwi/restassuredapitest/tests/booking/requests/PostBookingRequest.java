package br.com.cwi.restassuredapitest.tests.booking.requests;

import br.com.cwi.restassuredapitest.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class PostBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Cria uma nova reserva")
    public Response createBooking(){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.validBookingPayload().toString())
                .post("booking");
    }

    @Step("Cria uma nova reserva com payload inválido")
    public Response createBookingWlthInvalidPayload(){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.invalidPostBookingPayload().toString())
                .post("booking");
    }

    @Step("Cria uma nova reserva com parâmetros extras no payload")
    public Response createBookingWithExtraPayloadParameters(){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.validPostBookingPayloadWithExtraParameters().toString())
                .post("booking");
    }

    @Step("Cria uma nova reserva com valor inválido no header Accept")
    public Response createBookingWithInvalidAcceptHeader(){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "accept/invalido")
                .when()
                .body(bookingPayloads.validBookingPayload().toString())
                .post("booking");
    }

}
