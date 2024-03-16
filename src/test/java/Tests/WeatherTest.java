package Tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class WeatherTest {
    @BeforeEach
    public void setup () {
        RestAssured.useRelaxedHTTPSValidation(); // eliminuje problemy z HTTPS
        RestAssured.baseURI = "https://api.openweathermap.org";
        // RestAssured.basePath = "/data/2.5/weather";
        // Przydaje sie gdy korzystamy z mocka: RestAssured.port = 8080;
    }


    @Test
    public void shouldGetWeatherForLocation() {
        given()
                .param("appid","c2506a1304d08753f24a65ebfc1ef151")
                .param("id","6695624")
                .log()
                .all()
                .when()
                .get("/data/2.5/forecast")
                .then()
                .statusCode(200)
                .log().all();
    }
}
