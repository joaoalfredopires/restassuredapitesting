package br.com.cwi.restassuredapitest.runners;

import br.com.cwi.restassuredapitest.tests.booking.tests.DeleteBookingTest;
import br.com.cwi.restassuredapitest.tests.booking.tests.GetBookingTest;
import br.com.cwi.restassuredapitest.tests.booking.tests.PostBookingTest;
import br.com.cwi.restassuredapitest.tests.booking.tests.PutBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.cwi.restassuredapitest.suites.End2EndTests.class)
@Suite.SuiteClasses({

        GetBookingTest.class,
        DeleteBookingTest.class,
        PutBookingTest.class,
        PostBookingTest.class
})

public class End2EndTests {
}
