package base;

import configuration.AppProperties;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.MockStudent;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class TestBase {

    private static AppProperties appProperties;

    @BeforeAll
    public static void setup() {
        appProperties = AppProperties.getInstance();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(System.getProperty("baseUri"))
                .addParam("appid", System.getProperty("appid"))
                .setContentType(ContentType.JSON)
                .build()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());  // different way to display logs. For example when classic way is not working with RespSpec
    }
}
