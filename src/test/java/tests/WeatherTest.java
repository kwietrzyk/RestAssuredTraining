package tests;

import datastorage.DataStore;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class WeatherTest {

    private Cookies cookie;

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

    @Test
    public void shouldGetWeatherLocationWithCookies() {
        cookie = shouldLogIntoSystem();
        given()
                .cookies(cookie)
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

    private Cookies shouldLogIntoSystem() {
        String body = "payload";
        return RestAssured
                .given()
                .param("appid","c2506a1304d08753f24a65ebfc1ef151")
                .param("id","6695624")
                .log()
                .all()
                .body(body)
                .when()
                .post("/api/bbh")
                .getDetailedCookies();
    }

    @Test
    public void shouldGetWeatherForLocationWithResponseValidation() {
        DataStore.windDegree = given()
                .param("appid", "c2506a1304d08753f24a65ebfc1ef151")
                .param("q", "London,uk")
                .log()
                .all()
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .jsonPath()
                .get("wind.deg");

        System.out.println("Extracted value for wind Degree: " + DataStore.windDegree);
    }

    @Test
    public void shouldGetWeatherForLocationWithWindSpeedValidation() {
        given()
                .param("appid", "c2506a1304d08753f24a65ebfc1ef151")
                .param("q", "London,uk")
                .log()
                .all()
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .statusCode(200)
                .log()
                .all()
                //.body("wind.speed", greaterThan(5.0f), "name", equalTo("Ladek")); // soft assertion
                .body("wind.speed", greaterThan(3.0f))
                .body("name", equalTo("London"))
                .body("sys", hasEntry("country", "GB"))
                .body("sys", not(empty()));
    }
}
