package br.com.cwi.restassuredapitest.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os ids da listagem de reservas")
    public Response bookingReturnIds(){

        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna uma reserva específica")
    public Response bookingReturnSpecificBooking(int id){

        return given()
                .header("Accept","application/json")
                .when()
                .get("booking/" + id);
    }


}
