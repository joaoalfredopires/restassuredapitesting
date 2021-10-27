package br.com.cwi.restassuredapitest.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;


import java.text.SimpleDateFormat;

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

    @Step("Retorna as reservas com um 'firstname' específico")
    public Response bookingReturnFilterFirstname(String firstname){

        return given()
                .header("Accept","application/json")
                .when()
                .get("booking?firstname=" + firstname);
    }

    @Step("Retorna as reservas com um 'lastname' específico")
    public Response bookingReturnFilterLasttname(String lastname){

        return given()
                .when()
                .get("booking?lastname=" + lastname);
    }

    @Step("Retorna as reservas com data de checkin maior ou igual a informada")
    public Response bookingReturnFilterCheckin(String date){

        return given()
                .when()
                .get("booking?checkin=" + date);
    }

    @Step("Retorna as reservas com data de checkout maior ou igual a informada")
    public Response bookingReturnFilterCheckout(String date){

        return given()
                .when()
                .get("booking?checkout=" + date);
    }
}
