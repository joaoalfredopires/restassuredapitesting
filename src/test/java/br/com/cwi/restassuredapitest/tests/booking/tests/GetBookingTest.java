package br.com.cwi.restassuredapitest.tests.booking.tests;

import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.AcceptanceTests;
import br.com.cwi.restassuredapitest.suites.AllTests;
import br.com.cwi.restassuredapitest.suites.ContractTests;
import br.com.cwi.restassuredapitest.suites.SchemaTests;
import br.com.cwi.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.cwi.restassuredapitest.utils.Utils;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature - Retorno de reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    //Extrai o id do primeiro objeto do array
    int primeroId = getBookingRequest.bookingReturnIds()
            .then()
            .statusCode(200)
            .extract()
            .path("[0].bookingid");

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas")
    public void validadeBookingIdList() throws Exception{

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar uma reserva específica")
    public void validateSpecificBooking() throws Exception{
        getBookingRequest.bookingReturnSpecificBooking(primeroId)
                .then()
                .statusCode(200)
                .body("firstname", notNullValue(), "lastname", notNullValue());
                //Validando o body() por firstname e lastname porque são "required"

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro firstname")
    public void validadeBookingIdListFilterFirstName() throws Exception{

        Faker faker = new Faker();

        getBookingRequest.bookingReturnFilterFirstname(faker.elderScrolls().firstName())
                .then()
                .statusCode(200); //Validação do body() não se aplica, pois o teste quebraria com um firstname inexistente,
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro lastname")
    public void validadeBookingIdListFilterLastName() throws Exception{

        Faker faker = new Faker();

        getBookingRequest.bookingReturnFilterLasttname(faker.elderScrolls().lastName())
                .then().log().all()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro checkin")
    public void validadeBookingIdListFilterCheckin() throws Exception{

        getBookingRequest.bookingReturnFilterCheckin("2018-01-01")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro checkout")
    public void validadeBookingIdListFilterCheckout() throws Exception{

        getBookingRequest.bookingReturnFilterCheckout("2019-01-01")
                .then().log().all()
                .statusCode(200);
    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class, SchemaTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reservas")
    public void schemaValidationOfBookingList() throws Exception{

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class, SchemaTests.class})
    @DisplayName("Garantir o schema de retorno de uma reserva específica")
    public void schemaValidationOfSpecificBooking() throws Exception{

        getBookingRequest.bookingReturnSpecificBooking(primeroId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "specificbooking"))));
    }
}
