package br.com.cwi.restassuredapitest.tests.booking.tests;

import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.AllTests;
import br.com.cwi.restassuredapitest.tests.auth.requests.PostAuthRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.PutBookingRequest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Category(AllTests.class)
    public void validarAlteracaoDeUmaReservaUtilizandoToken(){

        int primeroId = getBookingRequest.bookingReturnIds()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeroId, postAuthRequest.getToken())
                 .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
