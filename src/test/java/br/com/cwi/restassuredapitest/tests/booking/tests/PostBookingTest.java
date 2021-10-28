package br.com.cwi.restassuredapitest.tests.booking.tests;

import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.AcceptanceTests;
import br.com.cwi.restassuredapitest.suites.AllTests;
import br.com.cwi.restassuredapitest.suites.End2EndTests;
import br.com.cwi.restassuredapitest.suites.SmokeTests;
import br.com.cwi.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.cwi.restassuredapitest.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    //Testes da suíte Acceptance do desafio - Início

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class, AcceptanceTests.class})
    @DisplayName("Validar a criação de uma nova reserva")
    public void validateBookingCreation() throws Exception {

        postBookingRequest.createBooking()
                .then()
                .statusCode(200).log().all()
                .body("size()", greaterThan(0));

    }

    //Testes da suíte Acceptance do desafio - Fim

    /*----------------------------------------------------------------------------------------------------------------*/

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, End2EndTests.class})
    @DisplayName("Validar erro de servidor 500 ao tentar criar uma reserva com payload inválido")
    public void validateServerErrorBookingCreationWithInvalidPayload() throws Exception {

        postBookingRequest.createBookingWlthInvalidPayload()
                .then()
                .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Category({AllTests.class, End2EndTests.class})
    @DisplayName("Validar a criação de uma nova reserva com parâmetros extra no payload")
    public void validateBookingCreationWithExtraPayloadParameters() throws Exception {

        postBookingRequest.createBookingWithExtraPayloadParameters()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
                //Reserva é criada com sucesso, porém os parâmetros extra são ignorados
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, End2EndTests.class})
    @DisplayName("Validar a criação de uma nova reserva com o header Accept inválido")
    public void validateBookingCreationWithInvalidAcceptHeader() throws Exception {

        postBookingRequest.createBookingWithInvalidAcceptHeader()
                .then()
                .statusCode(418);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, End2EndTests.class, SmokeTests.class})
    @DisplayName("Validar a criação de mais de uma reserva em sequência")
    public void validateMoreThanOneBookingCreation() throws Exception {

        postBookingRequest.createBooking()
                .then()
                .statusCode(200).log().all()
                .body("size()", greaterThan(0));

        postBookingRequest.createBooking()
                .then()
                .statusCode(200).log().all()
                .body("size()", greaterThan(0));

        postBookingRequest.createBooking()
                .then()
                .statusCode(200).log().all()
                .body("size()", greaterThan(0));

    }
}