package br.com.cwi.restassuredapitest.runners;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.cwi.restassuredapitest.suites.End2EndTests.class)
@Suite.SuiteClasses({

})

public class End2EndTests {
}
