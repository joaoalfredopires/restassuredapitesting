package br.com.cwi.restassuredapitest.tests.ping.tests;

import br.com.cwi.restassuredapitest.base.BaseTest;
import br.com.cwi.restassuredapitest.suites.AllTests;
import br.com.cwi.restassuredapitest.tests.ping.requests.GetPingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Test;
import org.junit.experimental.categories.Category;



public class GetPingTest extends BaseTest {

    GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Category({AllTests.class})
    @Severity(SeverityLevel.CRITICAL)
    public void validaApiOnline() throws Exception{

        getPingRequest.pingReturnApi()
                .then()
                .statusCode(201);
    }

}
