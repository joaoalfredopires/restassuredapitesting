package br.com.cwi.restassuredapitest.tests.booking.tests;


import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.*;
import br.com.cwi.restassuredapitest.tests.auth.requests.PostAuthRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.DeleteBookingRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Random;

import static java.lang.Math.abs;

@Feature("Feature - Excluir reserva")
public class DeleteBookingTest extends BaseTest {

    Random random = new Random();

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    //Testes da suíte Acceptance do desafio - Início

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, SmokeTests.class, AcceptanceTests.class})
    @DisplayName("Excluir uma reserva com sucesso")
    public void deleteBookingTest() throws Exception{

        int primeroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBookingWithToken(primeroId, postAuthRequest.getToken())
                .then()
                .statusCode(201);
    }

    //Testes da suíte Acceptance do desafio - Fim

    /*----------------------------------------------------------------------------------------------------------------*/

    //Testes da suíte E2e do desafio - Início

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Category({AllTests.class, End2EndTests.class})
    @DisplayName("Excluir uma reserva inxistente")
    public void deleteNonexistentBookingTest() throws Exception{

        deleteBookingRequest.deleteBookingWithToken(999999999, postAuthRequest.getToken())
                .then()
                .statusCode(201);
        //Este teste quebra propositalmente pois o id inexistente provoca um statusCode(405)
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, End2EndTests.class, SecurityTests.class})
    @DisplayName("Excluir uma reserva sem autorização")
    public void deleteBookingWithoutAutorizationTest() throws Exception{

        int primeroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBookingWithToken(primeroId, "token=tokendementirinha")
                .then()
                .statusCode(201);
        //Este teste quebra propositalmente pois a o token inválido provoca um statusCode(403)
    }

    //Testes da suíte E2e do desafio - Fim
}
