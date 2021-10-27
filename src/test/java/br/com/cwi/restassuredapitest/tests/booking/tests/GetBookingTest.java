package br.com.cwi.restassuredapitest.tests.booking.tests;

import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.AcceptanceTests;
import br.com.cwi.restassuredapitest.suites.AllTests;
import br.com.cwi.restassuredapitest.suites.ContractTests;
import br.com.cwi.restassuredapitest.suites.SchemaTests;
import br.com.cwi.restassuredapitest.tests.booking.requests.GetBookingRequest;
import br.com.cwi.restassuredapitest.utils.Utils;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.time.LocalDate;
import java.util.Random;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature - Retorno de reservas")
public class GetBookingTest extends BaseTest {

    //Instancialização de classes usadas nos testes

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    //Gera valores aleatórios
    Faker faker = new Faker();


    //Gera datas aleatórias
    Random random = new Random();
    LocalDate randomCheckinDate = LocalDate.of(random.nextInt(2021 - 2018) + 2018, random.nextInt(11) + 1, random.nextInt(30) +1);
    LocalDate randomCheckoutDate = LocalDate.of(random.nextInt(2021 - 2018) + 2018, random.nextInt(11) + 1, random.nextInt(30) +1);

    //Extrai o id do primeiro objeto do array
    int primeroId = getBookingRequest.bookingReturnIds()
            .then()
            .statusCode(200)
            .extract()
            .path("[0].bookingid");


    //Testes da suíte schema do desafio - início

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

    //Testes da suíte schema do desafio - fim

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
                //Validando o body() por firstname e lastname por serem "required"

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro firstname")
    public void validadeBookingIdListFilterFirstName() throws Exception{

        getBookingRequest.bookingReturnFilterFirstname(faker.elderScrolls().firstName())
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro lastname")
    public void validadeBookingIdListFilterLastName() throws Exception{

        getBookingRequest.bookingReturnFilterLasttname(faker.elderScrolls().lastName())
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro checkin")
    public void validadeBookingIdListFilterCheckin() throws Exception{

        getBookingRequest.bookingReturnFilterCheckin(randomCheckinDate)
                .then()
                .statusCode(200);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar ids de reservas utilizando o filtro checkout")
    public void validadeBookingIdListFilterCheckout() throws Exception{

        getBookingRequest.bookingReturnFilterCheckout(randomCheckoutDate)
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Tenta listar ids de reservas utilizando dois filtros de checkout")
    public void validadeBookingIdListFilterCheckoutCheckout() throws Exception{

        getBookingRequest.bookingReturnFilterCheckoutAndCheckout(randomCheckinDate, randomCheckoutDate)
                .then()
                .statusCode(200); //Este teste quebra propositalmente, pois o filtro inválido retorna um statusCode(500)
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Tenta listar ids de reservas utilizando os filtros name, checkin e checkout")
    public void validadeBookingIdListFilterNameCheckinCheckout() throws Exception{

        getBookingRequest.bookingReturnFilterNameAndCheckinAndCheckout(faker.dragonBall().character(),faker.elderScrolls().lastName(), randomCheckinDate, randomCheckoutDate)
                .then().log().all()
                .statusCode(200);

    }

}
