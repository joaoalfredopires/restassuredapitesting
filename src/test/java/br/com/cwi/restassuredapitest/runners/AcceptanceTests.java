package br.com.cwi.restassuredapitest.runners;

import br.com.cwi.restassuredapitest.tests.auth.tests.PostAuthTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.cwi.restassuredapitest.suites.AcceptanceTests.class)
@Suite.SuiteClasses({
        AcceptanceTests.class
})

public class AcceptanceTests {
}
