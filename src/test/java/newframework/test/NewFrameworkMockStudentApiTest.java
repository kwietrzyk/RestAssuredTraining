package newframework.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import models.MockStudent;
import newframework.base.NewFrameworkTestBase;
import newframework.client.ApiClient;
import newframework.factories.StudentFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wiremock.org.apache.commons.lang3.RandomStringUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NewFrameworkMockStudentApiTest extends NewFrameworkTestBase {

    private ApiClient I_want;
    private StudentFactory studentFactory;
    private final String basePathMockGet = "/api/studentsDetails/.*";
    private final String basePathMockPost = "/api/studentsDetails";

    @BeforeEach
    public void setupClient() {
        I_want = createApiClient(mock.baseUrl());  // variable name "I_want" to pretty read test line
        studentFactory = new StudentFactory();
        mock.stubFor(post(urlPathMatching(basePathMockGet)).willReturn(aResponse().withStatus(HttpStatus.SC_CREATED)));

    }

    @Test
    public void shouldGetMockStudent() throws JsonProcessingException {
        String studentName = RandomStringUtils.random(8, true, false).toLowerCase();
        MockStudent mockStudent = new MockStudent(studentName);
        mock.stubFor(get(urlPathMatching(basePathMockGet)).willReturn(aResponse()
                .withStatus(HttpStatus.SC_OK)
                .withHeader("Content-Type", ContentType.JSON.toString())
                .withBody(writer.writeValueAsString(mockStudent))));

        assertThat(I_want.getMockStudent(studentName).execute().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

}
