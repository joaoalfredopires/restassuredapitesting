package br.com.cwi.restassuredapitest.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;


import java.time.LocalDate;

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
    public Response bookingReturnFilterCheckin(LocalDate date){

        return given()
                .when()
                .get("booking?checkin=" + date);
    }

    @Step("Retorna as reservas com data de checkout maior ou igual a informada")
    public Response bookingReturnFilterCheckout(LocalDate date){

        return given()
                .when()
                .get("booking?checkout=" + date);
    }

    @Step("Retorna as reservas aplicando dois filtros de checkout na requisição")
    public Response bookingReturnFilterCheckoutAndCheckout(LocalDate date1, LocalDate date2){

        return given()
                .when()
                .get("booking?checkout=" + date1 + "&checkout=" +date2);
    }

    @Step("Retorna as reservas aplicando os filtros: firstname, lastname, chackin e checkout na requisição")
    public Response bookingReturnFilterNameAndCheckinAndCheckout(String firstname, String lastname, LocalDate date1, LocalDate date2){

        return given()
                .when()
                .get("booking?firstname="+ firstname +"&lastname="+ lastname +"&checkout="+ date1 +"&checkin="+date2);
    }

    @Step("Tenta retornar uma reserva específica utilizando um filtro formatado incorretamente")
    public Response bookingReturnWronglyFormattedFilter(){

        return given()
                .header("Accept","application/json")
                .when()
                .get("booking?fristnome=Batatinha123*lastnamis=Batima");
    }
}
