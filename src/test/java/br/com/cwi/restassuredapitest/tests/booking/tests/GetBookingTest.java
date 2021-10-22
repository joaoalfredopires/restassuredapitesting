package br.com.cwi.restassuredapitest.tests.booking.tests;

import br.com.cwi.restassuredapitest.suites.AllTests;
import br.com.cwi.restassuredapitest.tests.booking.requests.GetBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

public class GetBookingTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Category({AllTests.class})
    public void validaListagemdeIdsDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
