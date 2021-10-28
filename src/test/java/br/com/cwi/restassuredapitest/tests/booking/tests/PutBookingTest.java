package br.com.cwi.restassuredapitest.tests.booking.tests;

import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.*;
import br.com.cwi.restassuredapitest.tests.auth.requests.PostAuthRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Atualização de reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    //Testes da suíte Acceptance do desafio - Início

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class, SmokeTests.class})
    @DisplayName("Validar alteração de uma reserva utilizando token")
    public void validateBookingUpdateUsingToken() throws Exception{

        int primeroId = getBookingRequest.bookingReturnIds()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("[0].bookingid");

        putBookingRequest.updateBookingWithToken(primeroId, postAuthRequest.getToken())
                 .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class, SmokeTests.class})
    @DisplayName("Validar a alteração uma reserva utilizando Basic Authorization")
    public void validateBookingUpdateUsingBasicAuth() throws Exception{

        int primeroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingWithBasicAuthorization(primeroId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    //Testes da suíte Acceptance do desafio - Fim

    /*----------------------------------------------------------------------------------------------------------------*/

    //Testes da suíte E2e do desafio - Início

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, End2EndTests.class, SecurityTests.class})
    @DisplayName("Validar alteração de uma reserva sem informar token")
    public void validateBookingUpdateWithoutToken() throws Exception{

        int primeroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingWithouHeaderToken(primeroId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
        //Este teste falha propositalmente pois ao não informar o token retorna o statusCode(403)
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, End2EndTests.class, SecurityTests.class})
    @DisplayName("Validar alteração de uma reserva informando token inválido")
    public void validateBookingUpdateUsingInvalidToken() throws Exception{

        int primeroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingWithToken(primeroId, "token=tokeninvalido123")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
        //Este teste falha propositalmente pois ao informar token inválido retorna o statusCode(403)
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Category({AllTests.class, End2EndTests.class})
    @DisplayName("Validar alteração de uma reserva não existente")
    public void validateNonexistentBookingUpdate() throws Exception{

        putBookingRequest.updateBookingWithToken(99999998, postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
        //Este teste falha propositalmente pois ao informar uma reserva inexistente retorna o statusCode(403)
    }

    //Testes da suíte E2e do desafio - Fim
}
