package br.com.cwi.restassuredapitest.tests.booking.tests;


import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.AcceptanceTests;
import br.com.cwi.restassuredapitest.suites.AllTests;
import br.com.cwi.restassuredapitest.tests.auth.requests.PostAuthRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.DeleteBookingRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature - Excluir reserva")
public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Excluir uma reserva com sucesso")
    public void deleteBookingTest() throws Exception{

        int primeroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBookingToken(primeroId, postAuthRequest.getToken())
                .then()
                .statusCode(201);
    }
}
