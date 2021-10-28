package br.com.cwi.restassuredapitest.runners;

import br.com.cwi.restassuredapitest.tests.auth.tests.PostAuthTest;
import br.com.cwi.restassuredapitest.tests.booking.tests.DeleteBookingTest;
import br.com.cwi.restassuredapitest.tests.booking.tests.GetBookingTest;
import br.com.cwi.restassuredapitest.tests.booking.tests.PostBookingTest;
import br.com.cwi.restassuredapitest.tests.booking.tests.PutBookingTest;
import br.com.cwi.restassuredapitest.tests.ping.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.cwi.restassuredapitest.suites.SmokeTests.class)
@Suite.SuiteClasses({
        PostAuthTest.class,
        PostBookingTest.class,
        PutBookingTest.class,
        DeleteBookingTest.class,
        GetBookingTest.class,
        GetPingTest.class
})

public class SmokeTests {
}
