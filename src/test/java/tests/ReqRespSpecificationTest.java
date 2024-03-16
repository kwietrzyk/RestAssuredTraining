package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class ReqRespSpecificationTest {

    @BeforeEach
    public void setup () {
        RestAssured.useRelaxedHTTPSValidation(); // eliminuje problemy z HTTPS
        RestAssured.baseURI = "https://api.openweathermap.org";
    }

    @Test
    public void shouldGetWeatherForLocation() {
        RequestSpecification reqSpec = given()
                .param("appid","c2506a1304d08753f24a65ebfc1ef151")
                .param("id","6695624")
                .log()
                .all();

        ResponseSpecification respSpec = RestAssured.expect();
        respSpec.contentType(ContentType.JSON).statusCode(200).log().all().body("wind.speed", greaterThan(2.0f));

        given().spec(reqSpec)
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .spec(respSpec)
                .log()
                .all();
        // cos tu sie powalilo i restResponse nie loguje
    }
}
