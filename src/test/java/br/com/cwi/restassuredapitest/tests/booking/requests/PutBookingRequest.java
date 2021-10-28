package br.com.cwi.restassuredapitest.tests.booking.requests;

import br.com.cwi.restassuredapitest.tests.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Atualiza uma reserva específica com o parâmetro token")
    public Response updateBookingWithToken(int id, String token){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.validBookingPayload().toString())
                .put("booking/"+ id);
    }

    @Step("Atualiza uma reserva específica com Basic Authorization")
    public Response updateBookingWithBasicAuthorization(int id){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(bookingPayloads.validBookingPayload().toString())
                .put("booking/"+ id);
    }

    @Step("Atualiza uma reserva específica sem enviar token")
    public Response updateBookingWithouHeaderToken(int id){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.validBookingPayload().toString())
                .put("booking/"+ id);
    }
}
