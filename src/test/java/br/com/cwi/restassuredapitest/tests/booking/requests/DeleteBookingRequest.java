package br.com.cwi.restassuredapitest.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    @Step("Deleta a reserva de acordo com o id da lista de reservas")
    public Response deleteBookingToken(int id, String token){

        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", token)
                .when()
                .delete("booking/"+ id);

    }
}
