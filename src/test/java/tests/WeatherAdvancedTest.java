package tests;

import base.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import specifications.WeatherRequest;

import static io.restassured.RestAssured.given;
import static specifications.WeatherRequest.getRequestSpecification;
import static specifications.WeatherResponse.getResponseSpecification;

public class WeatherAdvancedTest extends TestBase {

    @ParameterizedTest
    @CsvFileSource(resources = "/cities.csv")
    @Tag("regression")
    @Tag("OP-1454")
    @DisplayName("Verify response with weather for selected cities")
    public void shouldGetWeatherByCity(String city, String country, int id) {
        given().spec(getRequestSpecification(id))
                .when().get()
                .then().spec(getResponseSpecification(city, country, id));
    }
}
