package br.com.cwi.restassuredapitest.runners;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.cwi.restassuredapitest.suites.SecurityTests.class)
@Suite.SuiteClasses({

})


public class SecutityTests {
}
