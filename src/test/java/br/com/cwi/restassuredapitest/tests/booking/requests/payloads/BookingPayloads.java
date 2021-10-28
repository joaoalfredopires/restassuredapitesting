package br.com.cwi.restassuredapitest.tests.booking.requests.payloads;

import com.github.javafaker.Faker;
import org.json.JSONObject;

import java.util.Random;

public class BookingPayloads {

    public static JSONObject validBookingPayload(){

        //Gera valores aleatórios
        Faker faker = new Faker();

        //Gera datas aleatórias
        Random random = new Random();   //Ano estático para garantir que a data de checkout é posterior a de checkin (caminho feliz)
        String randomCheckinDate = "2020-"+ (random.nextInt(11)+1) +"-"+ (random.nextInt(30)+1);
        String randomCheckoutDate = "2021-"+ (random.nextInt(11)+1) +"-"+ (random.nextInt(30)+1);

        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", randomCheckinDate);
        bookingDates.put("checkout", randomCheckoutDate);

        payload.put("firstname", faker.dragonBall().character());
        payload.put("lastname", faker.gameOfThrones().house());
        payload.put("totalprice", random.nextInt(5000));
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", faker.food().dish());

        return payload;
    }

    public static JSONObject invalidPostBookingPayload(){

        //Gera valores aleatórios
        Faker faker = new Faker();

        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2019-01-01");
        bookingDates.put("checkout", "2019-12-25");

        payload.put("fristname", "Maicon");  //firstname escrito errado
        payload.put("lastname", "Douglaix");
        payload.put("totalprice", 999);
        payload.put("depositpaid", false);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", faker.food().dish());

        return payload;
    }

    public static JSONObject validPostBookingPayloadWithExtraParameters(){

        //Gera valores aleatórios
        Faker faker = new Faker();

        //Gera datas aleatórias
        Random random = new Random();   //Ano estático para garantir que a data de checkout é posterior a de checkin (caminho feliz)
        String randomCheckinDate = "2020-"+ (random.nextInt(11)+1) +"-"+ (random.nextInt(30)+1);
        String randomCheckoutDate = "2021-"+ (random.nextInt(11)+1) +"-"+ (random.nextInt(30)+1);

        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", randomCheckinDate);
        bookingDates.put("checkout", randomCheckoutDate);

        payload.put("firstname", faker.harryPotter().character());
        payload.put("lastname", faker.gameOfThrones().house());
        payload.put("totalprice", random.nextInt(5000));
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", faker.food().dish());
        payload.put("childrennumber", random.nextInt(5));
        payload.put("guestjob", faker.job().title());

        return payload;
    }
}
