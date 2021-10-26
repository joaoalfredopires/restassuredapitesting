package br.com.cwi.restassuredapitest.runners;


import br.com.cwi.restassuredapitest.tests.ping.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.cwi.restassuredapitest.suites.HealthCheckTests.class)
@Suite.SuiteClasses({
        GetPingTest.class
})

public class HealthCheckTests {
}
