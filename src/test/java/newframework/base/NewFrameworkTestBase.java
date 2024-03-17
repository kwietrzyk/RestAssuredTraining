package newframework.base;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.MockStudent;
import newframework.client.ApiClient;
import newframework.client.JacksonMapper;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class NewFrameworkTestBase {

    @RegisterExtension
    protected static WireMockExtension mock = WireMockExtension.newInstance().options(wireMockConfig().dynamicPort()).build();
    protected static ObjectWriter writer = new JacksonMapper().create().writer().withDefaultPrettyPrinter();

    protected ApiClient createApiClient(String baseUri) {
        return new ApiClient(() -> new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setBaseUri(baseUri));
    }
}
