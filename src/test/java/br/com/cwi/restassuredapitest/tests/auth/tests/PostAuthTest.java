package br.com.cwi.restassuredapitest.tests.auth.tests;

import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.AllTests;
import br.com.cwi.restassuredapitest.suites.SmokeTests;
import br.com.cwi.restassuredapitest.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.notNullValue;

@Feature("Feature - Autenticação")
public class PostAuthTest extends BaseTest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Retornar um token para o usuário")
    public void validateTokenReturnToUser() throws Exception{
        postAuthRequest.tokenReturn()
                .then()
                .statusCode(200)
                .body("token", notNullValue());

    }
}
