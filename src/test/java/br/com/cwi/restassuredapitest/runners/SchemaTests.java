package br.com.cwi.restassuredapitest.runners;

import br.com.cwi.restassuredapitest.tests.booking.tests.GetBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.cwi.restassuredapitest.suites.SchemaTests.class)
@Suite.SuiteClasses({
        GetBookingTest.class
})

public class SchemaTests {
}
