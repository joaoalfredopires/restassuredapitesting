package br.com.cwi.restassuredapitest.runners;

import br.com.cwi.restassuredapitest.tests.auth.tests.PostAuthTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.cwi.restassuredapitest.suites.SmokeTests.class)
@Suite.SuiteClasses({
        PostAuthTest.class
})

public class SmokeTests {
}
