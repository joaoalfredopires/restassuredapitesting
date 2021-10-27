package br.com.cwi.restassuredapitest.tests.booking.requests.payloads;

import org.json.JSONObject;

public class BookingPayloads {

    public static JSONObject payloadValidUpdateBooking(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", "Lionel");
        payload.put("lastname", "Messi");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return payload;
    }

    public static JSONObject jsonAuthLogin(){

        JSONObject payloadLogin = new JSONObject();

        payloadLogin.put("username", "admin");
        payloadLogin.put("password", "password123");

        return payloadLogin;
    }
}
